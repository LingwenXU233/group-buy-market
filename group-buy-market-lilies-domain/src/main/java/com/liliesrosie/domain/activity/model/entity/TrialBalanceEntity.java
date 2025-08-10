package com.liliesrosie.domain.activity.model.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lingwenxu
 * @description Trial Balance entity object (used to display the group-buy discount information to the user)
 * @create 2025-08-10 12:10
 */
public class TrialBalanceEntity {

    /** goods ID */
    private String goodsId;

    /** goods name */
    private String goodsName;

    /** original price */
    private BigDecimal originalPrice;

    /** deduction price */
    private BigDecimal deductionPrice;

    /** group-buy target count for getting the deduction */
    private Integer targetCount;

    /** group-buy activity start time */
    private Date startTime;

    /** group-buy activity end time */
    private Date endTime;

    /** is the activity visible to user or not */
    private Boolean isVisible;

    /** is this user able to join the group-buy activity */
    private Boolean isEnable;

}
