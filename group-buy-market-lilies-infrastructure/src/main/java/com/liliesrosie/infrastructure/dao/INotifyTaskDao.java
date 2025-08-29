package com.liliesrosie.infrastructure.dao;

import com.liliesrosie.infrastructure.dao.po.NotifyTask;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-25 15:35
 */
@Mapper
public interface INotifyTaskDao {

    int insert(NotifyTask notifyTask);

    List<NotifyTask> queryUnExecutedNotifyTaskList();


    NotifyTask queryUnExecutedNotifyTaskListByTeamId(String teamId);

    int updateNotifyTaskStatusSuccess(String teamId);

    int updateNotifyTaskStatusError(String teamId);

    int updateNotifyTaskStatusRetry(String teamId);

}
