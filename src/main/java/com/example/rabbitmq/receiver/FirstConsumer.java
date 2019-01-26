package com.example.rabbitmq.receiver;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 消息消费者1
 * @author zhuzhe
 * @date 2018/5/25 17:32
 * @email 1529949535@qq.com
 */
@Component
public class FirstConsumer {

    /**
     * queues  指定从哪个队列（queue）订阅消息
     * @param message
     */
    @RabbitListener(queues = {"first-queue"})
    public void handleMessage(Message message){
        // 处理消息
        System.out.println("FirstConsumer {} handleMessage :"+message);
    }

//    @RabbitListener(queues = "queue-test")
//    public void process(Message message, AMQP.Channel channel) throws IOException {
//        // 采用手动应答模式, 手动确认应答更为安全稳定
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
//        log.info("receive: " + new String(message.getBody()));
//    }
}
