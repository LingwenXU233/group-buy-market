package com.liliesrosie.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-27 15:24
 */
@Configuration
public class OKHttpClientConfig {

    @Bean("OkHttpClient")
    public OkHttpClient httpClient() {
        return new OkHttpClient();
    }
}
