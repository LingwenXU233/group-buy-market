package com.liliesrosie.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lingwenxu
 * @description 用户拼单
 * @create 2025-08-20 13:34
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupBuyOrder {
    private Long id;
    private String teamId;
    private Long activityId;
    private String source;
    private String channel;
    private BigDecimal originalPrice;
    private BigDecimal deductionPrice;
    private BigDecimal payPrice;
    private Integer targetCount;
    private Integer completeCount;
    private Integer lockCount;
    private Integer status;
    private Date createTime;
    private Date updateTime;
    private Date validStartTime;
    private Date validEndTime;
    private String notifyUrl;
}
