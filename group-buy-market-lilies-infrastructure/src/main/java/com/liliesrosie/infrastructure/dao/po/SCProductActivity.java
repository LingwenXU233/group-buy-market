package com.liliesrosie.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-15 10:46
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SCProductActivity {
    private Long id;
    private String source;
    private String channel;
    private Long activityId;
    private String goodsId;
    private Date createTime;
    private Date updateTime;
}
