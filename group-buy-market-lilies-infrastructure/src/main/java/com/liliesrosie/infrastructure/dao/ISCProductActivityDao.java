package com.liliesrosie.infrastructure.dao;

import com.liliesrosie.infrastructure.dao.po.SCProductActivity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-15 10:52
 */
@Mapper
public interface ISCProductActivityDao {

    SCProductActivity querySCProductActivity(SCProductActivity scProductActivity);
}
