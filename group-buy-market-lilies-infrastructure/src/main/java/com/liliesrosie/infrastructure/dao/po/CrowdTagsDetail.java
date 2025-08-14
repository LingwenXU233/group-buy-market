package com.liliesrosie.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-14 14:27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrowdTagsDetail {
    private Long id;
    private String tagId;
    private String userId;
    private Date createTime;
    private Date updateTime;
}
