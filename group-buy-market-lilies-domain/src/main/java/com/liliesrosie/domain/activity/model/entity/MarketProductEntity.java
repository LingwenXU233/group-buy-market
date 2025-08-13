package com.liliesrosie.domain.activity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lingwenxu
 * @description Marketing product entity information, used to obtain product discount details based on this information
 * @create 2025-08-10 12:10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarketProductEntity {

    /** user ID */
    private String userId;

    /** product ID */
    private String goodsId;

    /** source */
    private String source;

    /** channel: the channel through which the user obtains the product information */
    private String channel;

}
