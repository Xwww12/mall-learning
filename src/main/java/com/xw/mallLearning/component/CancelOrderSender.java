package com.xw.mallLearning.component;

import com.xw.mallLearning.dto.QueueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 取消订单消息生产者
 */
@Slf4j
@Component
public class CancelOrderSender {
    @Resource
    private AmqpTemplate amqpTemplate;

    public void sendMessage(Long orderId, final Long delayTime) {
        // 消息处理器，设置超时时间
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(String.valueOf(delayTime));
                return message;
            }
        };
        // 发送消息
        amqpTemplate.convertAndSend(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange(), // 发送到的交换机
                QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey(), // 路由Key
                orderId,    // 消息
                messagePostProcessor    // 消息处理器
        );
        log.info("订单id:{}", orderId);
    }
}
