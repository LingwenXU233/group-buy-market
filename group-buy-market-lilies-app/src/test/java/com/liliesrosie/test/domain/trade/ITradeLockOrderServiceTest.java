package com.liliesrosie.test.domain.trade;

import com.alibaba.fastjson.JSON;
import com.liliesrosie.api.IMarketTradeService;
import com.liliesrosie.api.dto.LockMarketPayOrderRequestDTO;
import com.liliesrosie.api.dto.LockMarketPayOrderResponseDTO;
import com.liliesrosie.api.response.Response;
import com.liliesrosie.domain.activity.model.entity.MarketProductEntity;
import com.liliesrosie.domain.activity.model.entity.TrialBalanceEntity;
import com.liliesrosie.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.liliesrosie.domain.activity.service.IIndexGroupBuyMarketService;
import com.liliesrosie.domain.trade.model.entity.MarketPayOrderEntity;
import com.liliesrosie.domain.trade.model.entity.PayActivityEntity;
import com.liliesrosie.domain.trade.model.entity.PayDiscountEntity;
import com.liliesrosie.domain.trade.model.entity.UserEntity;
import com.liliesrosie.domain.trade.model.valobj.NotifyConfigVO;
import com.liliesrosie.domain.trade.model.valobj.NotifyTypeEnumVO;
import com.liliesrosie.domain.trade.service.ITradeLockOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-25 19:59
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ITradeLockOrderServiceTest {
    @Resource
    private IIndexGroupBuyMarketService indexGroupBuyMarketService;

    @Resource
    private ITradeLockOrderService tradeOrderService;

    @Resource
    private IMarketTradeService marketTradeService ;

    @Test
    public void test_lockMarketPayOrder() throws Exception {
        // 入参信息
        Long activityId = 100123L;
        String userId = "xfg08";
        String goodsId = "9890001";
        String source = "s01";
        String channel = "c01";
        String outTradeNo = RandomStringUtils.randomNumeric(12);
        String notifyUrl = "http://127.0.0.1:8091/api/v1/test/group_buy_notify";
        String notifyType = "http";

        // 1. 获取试算优惠，有【activityId】优先使用
        TrialBalanceEntity trialBalanceEntity = indexGroupBuyMarketService.indexMarketTrial(MarketProductEntity.builder()
                .userId(userId)
                .source(source)
                .channel(channel)
                .goodsId(goodsId)
//                .activityId(activityId)
                .build());

        GroupBuyActivityDiscountVO groupBuyActivityDiscountVO = trialBalanceEntity.getGroupBuyActivityDiscountVO();

        // 查询 outTradeNo 是否已经存在交易记录
        MarketPayOrderEntity marketPayOrderEntityOld = tradeOrderService.queryNoPayMarketPayOrderByOutTradeNo(userId, outTradeNo);
        if (null != marketPayOrderEntityOld) {
            log.info("测试结果(Old):{}", JSON.toJSONString(marketPayOrderEntityOld));
            return;
        }

        // 2. 锁定，营销预支付订单；商品下单前，预购锁定。
        MarketPayOrderEntity marketPayOrderEntityNew = tradeOrderService.lockMarketPayOrder(
                UserEntity.builder().userId(userId).build(),
                PayActivityEntity.builder()
                        .teamId("39932324")//null
                        .activityId(groupBuyActivityDiscountVO.getActivityId())
                        .activityName(groupBuyActivityDiscountVO.getActivityName())
                        .startTime(groupBuyActivityDiscountVO.getStartTime())
                        .endTime(groupBuyActivityDiscountVO.getEndTime())
                        .targetCount(groupBuyActivityDiscountVO.getTarget())
                        .validTime(groupBuyActivityDiscountVO.getValidTime())
                        .build(),
                PayDiscountEntity.builder()
                        .source(source)
                        .channel(channel)
                        .goodsId(goodsId)
                        .goodsName(trialBalanceEntity.getGoodsName())
                        .originalPrice(trialBalanceEntity.getOriginalPrice())
                        .deductionPrice(trialBalanceEntity.getDeductionPrice())
                        .outTradeNo(outTradeNo)
                        .notifyConfigVO(NotifyConfigVO.builder()
                                .notifyType(NotifyTypeEnumVO.valueOf(notifyUrl))
                                .notifyUrl(notifyUrl)
                                .build())
                        .build());

        log.info("测试结果(New):{}",JSON.toJSONString(marketPayOrderEntityNew));
    }

    @Test
    public void test_lockMarketPayOrder_feature13() throws Exception {
        // 要进行3笔锁单，锁单的时候，第一笔没有 teamId，后面2比要写入 teamId。
        // 多加了一个 notifyURL字段用于回调用
        LockMarketPayOrderRequestDTO lockMarketPayOrderRequestDTO = new LockMarketPayOrderRequestDTO();
        lockMarketPayOrderRequestDTO.setUserId("xlw01");
        lockMarketPayOrderRequestDTO.setTeamId(null);
        lockMarketPayOrderRequestDTO.setActivityId(100123L);
        lockMarketPayOrderRequestDTO.setGoodsId("9890001");
        lockMarketPayOrderRequestDTO.setSource("s01");
        lockMarketPayOrderRequestDTO.setChannel("c01");
        lockMarketPayOrderRequestDTO.setOutTradeNo(RandomStringUtils.randomNumeric(12));
        lockMarketPayOrderRequestDTO.setNotifyUrl("http://127.0.0.1:8091/api/v1/test/group_buy_notify");
        Response<LockMarketPayOrderResponseDTO> lockMarketPayOrderResponseDTOResponse = marketTradeService.lockMarketPayOrder(lockMarketPayOrderRequestDTO);
        log.info("测试结果 req:{} res:{}", JSON.toJSONString(lockMarketPayOrderRequestDTO), JSON.toJSONString(lockMarketPayOrderResponseDTOResponse));
    }

}
