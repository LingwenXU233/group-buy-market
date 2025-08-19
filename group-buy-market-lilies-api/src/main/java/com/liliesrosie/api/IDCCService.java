package com.liliesrosie.api;

import com.liliesrosie.api.response.Response;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-19 12:04
 */
public interface IDCCService {
    Response<Boolean> updateConfig(String key, String value);
}
