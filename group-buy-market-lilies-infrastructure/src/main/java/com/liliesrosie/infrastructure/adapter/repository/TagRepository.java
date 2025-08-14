package com.liliesrosie.infrastructure.adapter.repository;

import com.liliesrosie.domain.tag.adapter.repository.ITagRepository;
import com.liliesrosie.domain.tag.model.entity.CrowdTagsJobEntity;
import com.liliesrosie.infrastructure.dao.ICrowdTagsDao;
import com.liliesrosie.infrastructure.dao.ICrowdTagsDetailDao;
import com.liliesrosie.infrastructure.dao.ICrowdTagsJobDao;
import com.liliesrosie.infrastructure.dao.po.CrowdTags;
import com.liliesrosie.infrastructure.dao.po.CrowdTagsDetail;
import com.liliesrosie.infrastructure.dao.po.CrowdTagsJob;
import com.liliesrosie.infrastructure.redis.IRedisService;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBitSet;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author lingwenxu
 * @description Crowd Tag Repository Impl
 * @create 2025-08-14 14:52
 */
@Repository
public class TagRepository implements ITagRepository {

    @Resource
    ICrowdTagsDao crowdTagsDao;

    @Resource
    ICrowdTagsDetailDao crowdTagsDetailDao;

    @Resource
    ICrowdTagsJobDao crowdTagsJobDao;

    @Resource
    private IRedisService redisService;


    @Override
    public CrowdTagsJobEntity queryCrowdTagsJobEntity(String tagId, String batchId) {

        if(StringUtils.isBlank(tagId) || StringUtils.isBlank(batchId)){
            return null;
        }

        CrowdTagsJob crowdTagsJobReq = CrowdTagsJob.builder()
                                                .batchId(batchId)
                                                .tagId(tagId)
                                                .build();

        CrowdTagsJob crowdTagsJobRes = crowdTagsJobDao.queryCrowdTagsJob(crowdTagsJobReq);
        if (null == crowdTagsJobRes) return null;

        return CrowdTagsJobEntity.builder()
                .tagType(crowdTagsJobRes.getTagType())
                .tagRule(crowdTagsJobRes.getTagRule())
                .statStartTime(crowdTagsJobRes.getStatStartTime())
                .statEndTime(crowdTagsJobRes.getStatEndTime())
                .build();
    }

    @Override
    public void addCrowdTagsUserId(String tagId, String userId) {

        CrowdTagsDetail crowdTagsDetailReq = CrowdTagsDetail.builder()
                                                            .userId(userId)
                                                            .tagId(tagId)
                                                            .build();
        try {
            crowdTagsDetailDao.addCrowdTagsUserId(crowdTagsDetailReq);

            // 对每个规则对应于tagId，获取BitSet
            RBitSet bitSet = redisService.getBitSet(tagId);
            bitSet.set(redisService.getIndexFromUserId(userId), true);
        } catch (DuplicateKeyException ignore) {
            // 忽略唯一索引冲突
        }

    }

    @Override
    public void updateCrowdTagsStatistics(String tagId, int count) {
        CrowdTags crowdTagsReq = CrowdTags.builder()
                                            .tagId(tagId)
                                            .statistics(count)
                                            .build();

        crowdTagsDao.updateCrowdTagsStatistics(crowdTagsReq);
    }
}
