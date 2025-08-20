package com.liliesrosie.domain.trade.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-20 16:39
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupBuyProgressVO {
    /** 目标数量 */
    private Integer targetCount;
    /** 完成数量 */
    private Integer completeCount;
    /** 锁单数量 */
    private Integer lockCount;
}
