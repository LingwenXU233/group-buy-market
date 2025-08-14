package com.liliesrosie.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-14 14:29
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrowdTagsJob {

    private Long id;
    private String tagId;
    private String batchId;
    private Integer tagType;
    private String tagRule;
    private Date statStartTime;
    private Date statEndTime;
    private Integer status;
    private Date createTime;
    private Date updateTime;
}
