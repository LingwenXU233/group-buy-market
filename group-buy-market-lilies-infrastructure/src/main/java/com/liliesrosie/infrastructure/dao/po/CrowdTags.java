package com.liliesrosie.infrastructure.dao.po;

import lombok.*;

import java.util.Date;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-14 14:23
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrowdTags {

    private Long id;
    private String tagId;
    private String tagName;
    private String tagDesc;
    private Integer statistics;
    private Date createTime;
    private Date updateTime;

}
