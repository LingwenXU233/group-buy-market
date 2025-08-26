package com.liliesrosie.infrastructure.dao;

import com.liliesrosie.infrastructure.dao.po.NotifyTask;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-25 15:35
 */
@Mapper
public interface INotifyTaskDao {

    int insert(NotifyTask notifyTask);

}
