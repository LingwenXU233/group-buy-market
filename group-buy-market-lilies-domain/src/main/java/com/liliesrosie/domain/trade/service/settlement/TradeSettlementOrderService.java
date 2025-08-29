package com.liliesrosie.domain.trade.service.settlement;

import com.alibaba.fastjson.JSON;
import com.liliesrosie.domain.trade.adaptor.port.ITradePort;
import com.liliesrosie.domain.trade.adaptor.repository.ITradeRepository;
import com.liliesrosie.domain.trade.model.aggregate.GroupBuyTeamSettlementAggregate;
import com.liliesrosie.domain.trade.model.entity.*;
import com.liliesrosie.domain.trade.service.ITradeSettlementOrderService;
import com.liliesrosie.domain.trade.service.lock.factory.TradeLockRuleFilterFactory;
import com.liliesrosie.domain.trade.service.settlement.factory.TradeSettlementRuleFilterFactory;
import com.liliesrosie.types.design.framework.link.model2.BusinessLinkedList;
import com.liliesrosie.types.enums.NotifyTaskHTTPEnumVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-25 14:58
 */
@Slf4j
@Service
public class TradeSettlementOrderService implements ITradeSettlementOrderService {

    @Resource
    ITradePort tradePort;
    @Resource
    ITradeRepository repository;

    @Resource
    @Qualifier(("tradeSettlementRuleFilterChain"))
    BusinessLinkedList<TradeSettlementRuleCommandEntity, TradeSettlementRuleFilterFactory.DynamicContext, TradeSettlementRuleFilterBackEntity> tradeSettlementRuleFilter;



    @Override
    public TradePaySettlementEntity settlementMarketPayOrder(TradePaySuccessEntity tradePaySuccessEntity) throws Exception {

        log.info("拼团交易-支付订单结算:{} outTradeNo:{}", tradePaySuccessEntity.getUserId(), tradePaySuccessEntity.getOutTradeNo());

        // 1. 结算规则过滤
        TradeSettlementRuleFilterBackEntity tradeSettlementRuleFilterBackEntity = tradeSettlementRuleFilter.execute(TradeSettlementRuleCommandEntity.builder()
                .source(tradePaySuccessEntity.getSource())
                .channel(tradePaySuccessEntity.getChannel())
                .userId(tradePaySuccessEntity.getUserId())
                .outTradeNo(tradePaySuccessEntity.getOutTradeNo())
                .outTradeTime(tradePaySuccessEntity.getOutTradeTime())
                .build(),
                new TradeSettlementRuleFilterFactory.DynamicContext());

        String teamId = tradeSettlementRuleFilterBackEntity.getTeamId();

        // 2. 查询组团信息
        GroupBuyTeamEntity groupBuyTeamEntity = GroupBuyTeamEntity.builder()
                .teamId(tradeSettlementRuleFilterBackEntity.getTeamId())
                .activityId(tradeSettlementRuleFilterBackEntity.getActivityId())
                .targetCount(tradeSettlementRuleFilterBackEntity.getTargetCount())
                .completeCount(tradeSettlementRuleFilterBackEntity.getCompleteCount())
                .lockCount(tradeSettlementRuleFilterBackEntity.getLockCount())
                .status(tradeSettlementRuleFilterBackEntity.getStatus())
                .validStartTime(tradeSettlementRuleFilterBackEntity.getValidStartTime())
                .validEndTime(tradeSettlementRuleFilterBackEntity.getValidEndTime())
                .notifyUrl(tradeSettlementRuleFilterBackEntity.getNotifyUrl())
                .build();

        // 3. 构建聚合对象
        GroupBuyTeamSettlementAggregate groupBuyTeamSettlementAggregate = GroupBuyTeamSettlementAggregate.builder()
                .userEntity(UserEntity.builder().userId(tradePaySuccessEntity.getUserId()).build())
                .groupBuyTeamEntity(groupBuyTeamEntity)
                .tradePaySuccessEntity(tradePaySuccessEntity)
                .build();

        // 4. 拼团组团结算
        boolean isNotify = repository.settlementMarketPayOrder(groupBuyTeamSettlementAggregate);

        // 5. 组队回调处理 - 处理失败也会有定时任务补偿，通过这样的方式，可以减轻任务调度，提高时效性
        if (isNotify) {
            Map<String, Integer> notifyResultMap = execSettlementNotifyJob(teamId);
            log.info("回调通知拼团完结 result:{}", JSON.toJSONString(notifyResultMap));
        }

        // 6. 返回结算信息 - 公司中开发这样的流程时候，会根据外部需要进行值的设置
        return TradePaySettlementEntity.builder()
                .source(tradePaySuccessEntity.getSource())
                .channel(tradePaySuccessEntity.getChannel())
                .userId(tradePaySuccessEntity.getUserId())
                .teamId(teamId)
                .activityId(groupBuyTeamEntity.getActivityId())
                .outTradeNo(tradePaySuccessEntity.getOutTradeNo())
                .build();
    }

    @Override
    public Map<String, Integer> execSettlementNotifyJob() throws Exception {
        log.info("拼团交易-执行结算通知任务");

        // 查询未执行任务
        List<NotifyTaskEntity> notifyTaskEntityList = repository.queryUnExecutedNotifyTaskList();

        return this.execSettlementNotifyJob(notifyTaskEntityList);
    }

    @Override
    public Map<String, Integer> execSettlementNotifyJob(String teamId) throws Exception {
        log.info("拼团交易-执行结算通知任务");
        // 查询未执行任务
        List<NotifyTaskEntity> notifyTaskEntityList = repository.queryUnExecutedNotifyTaskList(teamId);
        return this.execSettlementNotifyJob(notifyTaskEntityList);
    }

    private Map<String, Integer> execSettlementNotifyJob(List<NotifyTaskEntity> notifyTaskEntityList) throws Exception{
        log.info("执行回掉头函数");

        int successCount = 0, errorCount = 0, retryCount = 0;
        for(NotifyTaskEntity notifyTaskEntity: notifyTaskEntityList){

            String response = tradePort.groupBuyNotify(notifyTaskEntity);

            // 更新状态判断&变更数据库表回调任务状态
            if (NotifyTaskHTTPEnumVO.SUCCESS.getCode().equals(response)) {
                int updateCount = repository.updateNotifyTaskStatusSuccess(notifyTaskEntity.getTeamId());
                if (updateCount == 1) {
                    successCount += 1;
                }
            } else if (NotifyTaskHTTPEnumVO.ERROR.getCode().equals(response)) {
                if (notifyTaskEntity.getNotifyCount() < 5) {
                    int updateCount = repository.updateNotifyTaskStatusError(notifyTaskEntity.getTeamId());
                    if (updateCount == 1) {
                        errorCount += 1;
                    }
                } else {
                    int updateCount = repository.updateNotifyTaskStatusRetry(notifyTaskEntity.getTeamId());
                    if (updateCount == 1) {
                        retryCount += 1;
                    }
                }

            }
        }

        // 将通知任务的执行状态返回
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("waitCount", notifyTaskEntityList.size());
        resultMap.put("successCount", successCount);
        resultMap.put("errorCount", errorCount);
        resultMap.put("retryCount", retryCount);

        return resultMap;
    }

}