package com.liliesrosie.domain.trade.model.aggregate;

import com.liliesrosie.domain.trade.model.entity.PayActivityEntity;
import com.liliesrosie.domain.trade.model.entity.PayDiscountEntity;
import com.liliesrosie.domain.trade.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lingwenxu
 * @description 锁单操作传入的对象
 * @create 2025-08-20 16:34
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupBuyOrderAggregate {
    UserEntity userEntity;
    PayActivityEntity payActivityEntity;
    PayDiscountEntity payDiscountEntity;
    int userTakeOrderCount;
}
