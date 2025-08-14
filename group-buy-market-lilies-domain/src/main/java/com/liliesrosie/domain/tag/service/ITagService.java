package com.liliesrosie.domain.tag.service;

/**
 * @author lingwenxu
 * @description 人群标签服务接口
 * @create 2025-08-14 14:41
 */
public interface ITagService {

    /**
     * 执行人群标签批次任务: 系统根据定义的规则，从业务数据（数仓）中采集符合条件的用户ID。
     *
     * @param tagId   人群ID
     * @param batchId 批次ID
     */
    void execTagBatchJob(String tagId, String batchId);
}
