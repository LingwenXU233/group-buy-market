package com.liliesrosie.domain.activity.model.valobj;

import com.liliesrosie.types.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-11 14:53
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupBuyActivityDiscountVO {

    /** 活动ID */
    private Long activityId;
    /** 活动名称 */
    private String activityName;
    /** 来源 */
    private String source;
    /** 渠道 */
    private String channel;
    /** 商品ID */
    private String goodsId;

    /** 折扣ID */
    private GroupBuyDiscount groupBuyDiscount;

    /** 拼团方式（0自动成团、1达成目标拼团） */
    private Integer groupType;
    /** 拼团次数限制 */
    private Integer takeLimitCount;
    /** 拼团目标 */
    private Integer target;
    /** 拼团时长（分钟） */
    private Integer validTime;
    /** 活动状态（0创建、1生效、2过期、3废弃） */
    private Integer status;
    /** 活动开始时间 */
    private Date startTime;
    /** 活动结束时间 */
    private Date endTime;
    /** 人群标签规则标识 */
    private String tagId;
    /** 人群标签规则范围 */
    private String tagScope;

    public Boolean isVisible(){
        // 没有配置限制，默认可见
        if(StringUtils.isBlank(this.tagScope))return TagScopeEnumVO.VISIBLE.getAllow();

        List<String> tagScopeList = Arrays.asList(tagScope.split(Constants.SPLIT));

        if(tagScopeList.contains(TagScopeEnumVO.VISIBLE.getOrder())){
            // 配置了可见限制 → 默认不可见
            return TagScopeEnumVO.VISIBLE.getRefuse();
        }
        // 没配置限制 → 可见
        return TagScopeEnumVO.VISIBLE.getAllow();

    }

    public Boolean isEnable(){
        // 没有配置限制，默认可参与
        if(StringUtils.isBlank(this.tagScope))return TagScopeEnumVO.ENABLE.getAllow();

        List<String> tagScopeList = Arrays.asList(tagScope.split(Constants.SPLIT));

        if(tagScopeList.contains(TagScopeEnumVO.ENABLE.getOrder())){
            // 配置了可见限制 → 默认不可参与
            return TagScopeEnumVO.ENABLE.getRefuse();
        }
        // 没配置限制 → 可参与
        return TagScopeEnumVO.ENABLE.getAllow();

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GroupBuyDiscount {
        /**
         * 折扣标题
         */
        private String discountName;

        /**
         * 折扣描述
         */
        private String discountDesc;

        /**
         * 折扣类型（0:base、1:tag）
         */
        private Byte discountType;

        /**
         * 营销优惠计划（ZJ:直减、MJ:满减、N元购）
         */
        private String marketPlan;

        /**
         * 营销优惠表达式
         */
        private String marketExpr;

        /**
         * 人群标签，特定优惠限定
         */
        private String tagId;


    }



}
