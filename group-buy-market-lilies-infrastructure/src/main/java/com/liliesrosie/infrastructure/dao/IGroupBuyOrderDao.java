package com.liliesrosie.infrastructure.dao;

import com.liliesrosie.infrastructure.dao.po.GroupBuyOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-20 13:44
 */
@Mapper
public interface IGroupBuyOrderDao {

    void insert(GroupBuyOrder groupBuyOrder);

    GroupBuyOrder queryGroupBuyProgress(String teamId);

    int updateAddLockCount(String teamId);

    int updateSubtractionLockCount(String teamId);

}
