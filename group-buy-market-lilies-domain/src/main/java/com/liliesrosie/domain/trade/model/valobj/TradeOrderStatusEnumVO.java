package com.liliesrosie.domain.trade.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public enum TradeOrderStatusEnumVO {

    CREATE(0, "初始创建"),
    COMPLETE(1, "消费完成"),
    CLOSE(2, "超时关闭"),
    ;

    private Integer code;
    private String info;

    TradeOrderStatusEnumVO(Integer code, String info){
        this.code = code;
        this.info = info;
    }

    public static TradeOrderStatusEnumVO valueOf(Integer code) {
        switch (code) {
            case 0:
                return CREATE;
            case 1:
                return COMPLETE;
            case 2:
                return CLOSE;
        }
        return CREATE;
    }

}
