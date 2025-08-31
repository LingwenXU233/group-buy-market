package com.liliesrosie.infrastructure.dao;

import com.liliesrosie.infrastructure.dao.po.GroupBuyOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

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

    GroupBuyOrder queryGroupBuyTeamByTeamId(String teamId);

    int updateOrderStatus2COMPLETE(String teamId);

    int updateAddCompleteCount(String teamId);

    List<GroupBuyOrder> queryGroupBuyTeamByTeamIds(Set<String> teamIds);

    int queryAllTeamCount(Set<String> teamIds);
    int queryAllTeamCompleteCount(Set<String> teamIds);
    int queryAllUserCount(Set<String> teamIds);

}
