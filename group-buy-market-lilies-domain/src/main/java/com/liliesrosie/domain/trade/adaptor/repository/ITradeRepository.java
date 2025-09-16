package com.liliesrosie.domain.trade.adaptor.repository;

import com.liliesrosie.domain.trade.model.aggregate.GroupBuyOrderAggregate;
import com.liliesrosie.domain.trade.model.aggregate.GroupBuyTeamSettlementAggregate;
import com.liliesrosie.domain.trade.model.entity.GroupBuyActivityEntity;
import com.liliesrosie.domain.trade.model.entity.GroupBuyTeamEntity;
import com.liliesrosie.domain.trade.model.entity.MarketPayOrderEntity;
import com.liliesrosie.domain.trade.model.entity.NotifyTaskEntity;
import com.liliesrosie.domain.trade.model.valobj.GroupBuyProgressVO;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-20 14:16
 */
public interface ITradeRepository {

    MarketPayOrderEntity queryNoPayMarketPayOrderByOutTradeNo(String userId, String outTradeNo);

    GroupBuyProgressVO queryGroupBuyProgress(String teamId);

    MarketPayOrderEntity lockMarketPayOrder(GroupBuyOrderAggregate groupBuyOrderAggregate);

    GroupBuyActivityEntity queryGroupBuyActivityEntityByActivityId(Long activityId);

    int queryOrderCountByActivityId(String userId, Long activityId);

    MarketPayOrderEntity queryMarketPayOrderEntityByOutTradeNo(String userId, String outTradeNo);

    GroupBuyTeamEntity queryGroupBuyTeamByTeamId(String teamId);

    NotifyTaskEntity settlementMarketPayOrder(GroupBuyTeamSettlementAggregate groupBuyTeamSettlementAggregate);

    boolean isSCBlackIntercept(String source, String channel);

    List<NotifyTaskEntity> queryUnExecutedNotifyTaskList();

    List<NotifyTaskEntity> queryUnExecutedNotifyTaskList(String teamId);

    int updateNotifyTaskStatusSuccess(String teamId);

    int updateNotifyTaskStatusError(String teamId);

    int updateNotifyTaskStatusRetry(String teamId);

    boolean occupyTeamSlot(Integer target, Integer validTime, String teamSlotKey, String recoveryTeamSlotKey);

    void recoveryTeamSlot(String recoveryTeamStockKey, Integer validTime);

}
