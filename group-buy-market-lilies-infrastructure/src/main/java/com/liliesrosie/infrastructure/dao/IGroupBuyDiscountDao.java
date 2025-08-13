package com.liliesrosie.infrastructure.dao;

import com.liliesrosie.infrastructure.dao.po.GroupBuyDiscount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IGroupBuyDiscountDao {

    List<GroupBuyDiscount> queryGroupBuyDiscountList();
    GroupBuyDiscount queryGroupBuyActivityDiscountByDiscountId(String discountId);

}
