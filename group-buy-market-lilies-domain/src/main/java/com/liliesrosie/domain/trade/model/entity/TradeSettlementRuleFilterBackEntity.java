package com.liliesrosie.domain.trade.model.entity;

import com.liliesrosie.domain.trade.model.valobj.GroupBuyOrderEnumVO;
import com.liliesrosie.domain.trade.model.valobj.NotifyConfigVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-26 10:48
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeSettlementRuleFilterBackEntity {
    /** 拼单组队ID */
    private String teamId;
    /** 活动ID */
    private Long activityId;
    /** 目标数量 */
    private Integer targetCount;
    /** 完成数量 */
    private Integer completeCount;
    /** 锁单数量 */
    private Integer lockCount;
    /** 状态（0-拼单中、1-完成、2-失败） */
    private GroupBuyOrderEnumVO status;
    /** 拼团开始时间 - 参与拼团时间 */
    private Date validStartTime;
    /** 拼团结束时间 - 拼团有效时长 */
    private Date validEndTime;
    /* 回调任务相关信息 */
    private NotifyConfigVO notifyConfigVO;
}
