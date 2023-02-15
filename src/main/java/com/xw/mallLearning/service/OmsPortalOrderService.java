package com.xw.mallLearning.service;

import com.xw.mallLearning.common.api.CommonResult;
import com.xw.mallLearning.dto.OrderParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单管理Service
 */
public interface OmsPortalOrderService {

    /**
     * 生成订单
     * @param orderParam
     * @return
     */
    @Transactional
    CommonResult generateOrder(OrderParam orderParam);

    @Transactional
    void cancelOrder(Long orderId);
}
