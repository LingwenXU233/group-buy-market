package com.liliesrosie.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TagScopeEnumVO {

    VISIBLE(true, false, "活动是否可见", "1"),
    ENABLE(true, false, "活动是否可参与", "2");

    private Boolean allow;
    private Boolean refuse;
    private String info;
    private String order;

}
