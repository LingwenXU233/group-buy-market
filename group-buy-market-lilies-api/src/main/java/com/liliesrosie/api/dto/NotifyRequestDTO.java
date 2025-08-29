package com.liliesrosie.api.dto;

import lombok.Data;

import java.util.List;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-27 17:51
 */
@Data
public class NotifyRequestDTO {
    /** 组队ID */
    private String teamId;
    /** 外部单号 */
    private List<String> outTradeNoList;

}
