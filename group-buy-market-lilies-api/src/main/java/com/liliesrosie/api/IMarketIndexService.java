package com.liliesrosie.api;

import com.liliesrosie.api.dto.GoodsMarketRequestDTO;
import com.liliesrosie.api.dto.GoodsMarketResponseDTO;
import com.liliesrosie.api.response.Response;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-29 11:44
 */
public interface IMarketIndexService {
    /**
     * 查询拼团营销配置
     *
     * @param goodsMarketRequestDTO 营销商品信息
     * @return 营销配置信息
     */
    Response<GoodsMarketResponseDTO> queryGroupBuyMarketConfig(GoodsMarketRequestDTO goodsMarketRequestDTO);

}
