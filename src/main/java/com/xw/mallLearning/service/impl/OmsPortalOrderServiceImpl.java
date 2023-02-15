package com.xw.mallLearning.service.impl;

import com.xw.mallLearning.common.api.CommonResult;
import com.xw.mallLearning.component.CancelOrderSender;
import com.xw.mallLearning.dto.OrderParam;
import com.xw.mallLearning.service.OmsPortalOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {

    @Resource
    private CancelOrderSender cancelOrderSender;

    @Override
    public CommonResult generateOrder(OrderParam orderParam) {
        // 下单操作，生成订单和订单id
        log.info("下单操作....");
        Long orderId = 10L;
        // 下完单后发送一个延迟消息，若期间没有付款则取消订单
        sendDelayMessageCancelOrder(orderId);
        return CommonResult.success(null, "下单成功");
    }

    @Override
    public void cancelOrder(Long orderId) {
        log.info("取消订单，id:{}", orderId);
    }

    private void sendDelayMessageCancelOrder(Long orderId) {
        // 30秒
        Long delayTimes = 30 * 1000L;
        cancelOrderSender.sendMessage(orderId, delayTimes);
    }
}
