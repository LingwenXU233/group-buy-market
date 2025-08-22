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
 * @create 2025-08-20 13:40
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupBuyOrderList {
    private Long id;
    private String userId;
    private String teamId;
    private String orderId;
    private Long activityId;
    private Date startTime;
    private Date endTime;
    private String goodsId;
    private String source;
    private String channel;
    private BigDecimal originalPrice;
    private BigDecimal deductionPrice;
    private Integer status;
    private String outTradeNo;
    private Date createTime;
    private Date updateTime;
    /** 唯一业务ID */
    private String bizId;
}
