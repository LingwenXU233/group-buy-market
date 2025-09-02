package com.liliesrosie.api;

import com.liliesrosie.api.dto.LockMarketPayOrderRequestDTO;
import com.liliesrosie.api.dto.LockMarketPayOrderResponseDTO;
import com.liliesrosie.api.dto.SettlementMarketPayOrderRequestDTO;
import com.liliesrosie.api.dto.SettlementMarketPayOrderResponseDTO;
import com.liliesrosie.api.response.Response;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-20 17:16
 */
public interface IMarketTradeService {
    Response<LockMarketPayOrderResponseDTO> lockMarketPayOrder(LockMarketPayOrderRequestDTO lockMarketPayOrderRequestDTO);

    public Response<SettlementMarketPayOrderResponseDTO> settlementMarketPayOrder(@RequestBody SettlementMarketPayOrderRequestDTO requestDTO);

}
