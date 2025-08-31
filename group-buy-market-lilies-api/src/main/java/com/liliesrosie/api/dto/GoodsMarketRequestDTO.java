package com.liliesrosie.api.dto;

import lombok.Data;

/**
 * @author lingwenxu
 * @description 商品营销请求对象
 * @create 2025-08-29 11:46
 */
@Data
public class GoodsMarketRequestDTO {

    // 用户ID
    private String userId;
    // 渠道
    private String source;
    // 来源
    private String channel;
    // 商品ID
    private String goodsId;

}
