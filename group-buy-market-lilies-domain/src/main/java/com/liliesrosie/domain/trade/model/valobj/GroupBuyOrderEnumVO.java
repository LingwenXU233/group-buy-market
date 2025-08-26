package com.liliesrosie.domain.trade.model.valobj;

import lombok.Getter;

@Getter
public enum GroupBuyOrderEnumVO {
    PROGRESS(0, "拼单中"),
    COMPLETE(1, "完成"),
    FAIL(2, "失败"),
    ;

    private Integer code;
    private String info;

    GroupBuyOrderEnumVO(Integer code, String info) {
        this.code = code;
        this.info = info;
    }


    public static GroupBuyOrderEnumVO valueOf(Integer code) {
        switch (code) {
            case 0:
                return PROGRESS;
            case 1:
                return COMPLETE;
            case 2:
                return FAIL;
        }
        throw new RuntimeException("err code not exist!");
    }

}
