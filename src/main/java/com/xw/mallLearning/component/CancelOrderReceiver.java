package com.xw.mallLearning.component;

import com.xw.mallLearning.service.OmsPortalOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 取消订单消息消费者
 */
@Slf4j
@Component
public class CancelOrderReceiver {
    @Resource
    private OmsPortalOrderService omsPortalOrderService;

    @RabbitListener(queues = "mall.order.cancel")
    public void handle(Long orderId) {
        omsPortalOrderService.cancelOrder(orderId);
        log.info("处理取消订单消息的id:{}", orderId);
    }
}
