package com.liliesrosie.domain.trade.service.filter;


import com.liliesrosie.domain.trade.adaptor.repository.ITradeRepository;
import com.liliesrosie.domain.trade.model.entity.GroupBuyActivityEntity;
import com.liliesrosie.domain.trade.model.entity.TradeRuleCommandEntity;
import com.liliesrosie.domain.trade.model.entity.TradeRuleFilterBackEntity;
import com.liliesrosie.domain.trade.service.factory.TradeRuleFilterFactory;
import com.liliesrosie.types.design.framework.link.model1.AbstractLogicNode;
import com.liliesrosie.types.design.framework.link.model2.ILogicHandler;
import com.liliesrosie.types.enums.ActivityStatusEnumVO;
import com.liliesrosie.types.enums.ResponseCode;
import com.liliesrosie.types.exception.AppException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-22 14:22
 */
@Slf4j
@Service
public class ActivityUsibilityRuleFilter implements ILogicHandler<TradeRuleCommandEntity, TradeRuleFilterFactory.DynamicContext, TradeRuleFilterBackEntity> {

    @Resource
    private ITradeRepository repository;

    @Override
    public TradeRuleFilterBackEntity apply(TradeRuleCommandEntity requestParam, TradeRuleFilterFactory.DynamicContext dynamicContext) throws Exception {
        log.info("交易规则过滤-活动的可用性校验{} activityId:{}", requestParam.getUserId(), requestParam.getActivityId());

        GroupBuyActivityEntity groupBuyActivity = repository.queryGroupBuyActivityEntityByActivityId(requestParam.getActivityId());

        // 校验；活动状态 - 可以抛业务异常code，或者把code写入到动态上下文dynamicContext中，最后获取。
        ActivityStatusEnumVO activityStatusEnumVO = groupBuyActivity.getStatus();
        if(!ActivityStatusEnumVO.EFFECTIVE.equals(activityStatusEnumVO)){
            log.info("活动的可用性校验，非生效状态 activityId:{}", requestParam.getActivityId());
            throw new AppException(ResponseCode.E0101.getCode(), ResponseCode.E0101.getInfo());
        }

        // 校验；活动时间
        Date currentTime = new Date();
        if (currentTime.before(groupBuyActivity.getStartTime()) || currentTime.after(groupBuyActivity.getEndTime())) {
            log.info("活动的可用性校验，非可参与时间范围 activityId:{}", requestParam.getActivityId());
            throw new AppException(ResponseCode.E0102.getCode(), ResponseCode.E0102.getInfo());
        }

        dynamicContext.setGroupBuyActivity(groupBuyActivity);

        return null;
    }

}
