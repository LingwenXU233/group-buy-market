package com.liliesrosie.domain.trade.service.lock.filter;

import com.liliesrosie.domain.trade.adaptor.repository.ITradeRepository;
import com.liliesrosie.domain.trade.model.entity.GroupBuyActivityEntity;
import com.liliesrosie.domain.trade.model.entity.TradeLockRuleCommandEntity;
import com.liliesrosie.domain.trade.model.entity.TradeLockRuleFilterBackEntity;
import com.liliesrosie.domain.trade.service.lock.factory.TradeLockRuleFilterFactory;
import com.liliesrosie.types.design.framework.link.model2.ILogicHandler;
import com.liliesrosie.types.enums.ResponseCode;
import com.liliesrosie.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lingwenxu
 * @description group slot occupation rule filtering
 * @create 2025-09-13 18:22
 */
@Slf4j
@Service
public class TeamStockOccupyRuleFilter implements ILogicHandler<TradeLockRuleCommandEntity, TradeLockRuleFilterFactory.DynamicContext, TradeLockRuleFilterBackEntity> {

    @Resource
    ITradeRepository repository;

    @Override
    public TradeLockRuleFilterBackEntity apply(TradeLockRuleCommandEntity requestParameter, TradeLockRuleFilterFactory.DynamicContext dynamicContext) throws Exception {
        log.info("交易规则过滤-组队库存校验{} activityId:{}", requestParameter.getUserId(), requestParameter.getActivityId());

        // 1. if teamId doesn't exist, 则为首次开团，不做拼团组队目标量库存限制
        String teamId = requestParameter.getTeamId();
        if (StringUtils.isBlank(teamId)) {
            return TradeLockRuleFilterBackEntity.builder()
                    .userTakeOrderCount(dynamicContext.getUserTakeOrderCount())
                    .build();
        }

        // 2. if teamId exist, 抢占队伍位置；通过抢占 Redis 缓存库存，来降低对数据库的行锁压力。
        GroupBuyActivityEntity groupBuyActivity = dynamicContext.getGroupBuyActivity();
        Integer target = groupBuyActivity.getTarget();
        Integer validTime = groupBuyActivity.getValidTime();
        String teamSlotKey = dynamicContext.generateTeamSlotKey(teamId);
        String recoveryTeamSlotKey = dynamicContext.generateRecoveryTeamSlotKey(teamId);
        boolean status = repository.occupyTeamSlot(target, validTime, teamSlotKey, recoveryTeamSlotKey);

        if(!status){
            log.warn("交易规则过滤-组队库存校验{} activityId:{} 抢占失败:{}", requestParameter.getUserId(), requestParameter.getActivityId(), teamSlotKey);
            throw new AppException(ResponseCode.E0008);
        }

        return TradeLockRuleFilterBackEntity.builder()
                .userTakeOrderCount(dynamicContext.getUserTakeOrderCount())
                .recoveryTeamSlotKey(recoveryTeamSlotKey)
                .build();
    }
}
