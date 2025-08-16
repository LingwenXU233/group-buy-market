package com.liliesrosie.infrastructure.dao;

import com.liliesrosie.infrastructure.dao.po.CrowdTagsDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-14 14:34
 */
@Mapper
public interface ICrowdTagsDetailDao {


    void addCrowdTagsUserId(CrowdTagsDetail crowdTagsDetailReq);

}
