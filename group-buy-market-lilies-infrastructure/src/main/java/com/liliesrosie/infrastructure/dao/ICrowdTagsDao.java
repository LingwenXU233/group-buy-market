package com.liliesrosie.infrastructure.dao;

import com.liliesrosie.infrastructure.dao.po.CrowdTags;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-14 14:33
 */
@Mapper
public interface ICrowdTagsDao {
    // update
    void updateCrowdTagsStatistics(CrowdTags crowdTagsReq);
}
