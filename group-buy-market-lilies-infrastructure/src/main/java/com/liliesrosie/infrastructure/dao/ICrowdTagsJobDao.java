package com.liliesrosie.infrastructure.dao;

import com.liliesrosie.infrastructure.dao.po.CrowdTagsJob;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-14 14:36
 */
@Mapper
public interface ICrowdTagsJobDao {
    CrowdTagsJob queryCrowdTagsJob(CrowdTagsJob crowdTagsJobReq);
}
