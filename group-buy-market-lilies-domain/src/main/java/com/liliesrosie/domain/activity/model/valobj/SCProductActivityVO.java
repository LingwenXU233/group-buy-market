package com.liliesrosie.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-15 11:18
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SCProductActivityVO {
    private String source;
    private String channel;
    private Long activityId;
    private String goodsId;
}
