package com.liliesrosie.domain.trade.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lingwenxu
 * @description  回调消息相关字段
 * @create 2025-09-02 09:08
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotifyConfigVO {
    /**
     * 回调方式；MQ、HTTP
     */
    private NotifyTypeEnumVO notifyType;
    /**
     * 回调消息
     */
    private String notifyMQ;
    /**
     * 回调地址
     */
    private String notifyUrl;
}
