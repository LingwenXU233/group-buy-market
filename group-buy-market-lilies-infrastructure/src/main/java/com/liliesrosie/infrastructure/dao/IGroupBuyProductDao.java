package com.liliesrosie.infrastructure.dao;

import com.liliesrosie.infrastructure.dao.po.GroupBuyProduct;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-11 15:01
 */
@Mapper
public interface IGroupBuyProductDao {
    GroupBuyProduct queryProductByGoodsIdList(String goodsId);
}
