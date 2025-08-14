package com.liliesrosie.domain.tag.adapter.repository;

import com.liliesrosie.domain.tag.model.entity.CrowdTagsJobEntity;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-14 14:50
 */
public interface ITagRepository {

    CrowdTagsJobEntity queryCrowdTagsJobEntity(String tagId, String batchId);

    void addCrowdTagsUserId(String tagId, String userId);

    void updateCrowdTagsStatistics(String tagId, int count);
}
