package com.liliesrosie.domain.trade.adaptor.port;

import com.liliesrosie.domain.trade.model.entity.NotifyTaskEntity;
import org.springframework.stereotype.Service;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-27 14:18
 */

public interface ITradePort {
    String groupBuyNotify(NotifyTaskEntity notifyTask) throws Exception;
}
