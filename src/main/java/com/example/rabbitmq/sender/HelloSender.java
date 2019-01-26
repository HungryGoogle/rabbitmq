package com.example.rabbitmq.sender;

import com.example.rabbitmq.config.RabbitMqConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

//RabbitTemplate.ConfirmCallback
@Service
public class HelloSender implements RabbitTemplate.ReturnCallback {
 
    @Autowired
//    private AmqpTemplate rabbitTemplate;
    private RabbitTemplate rabbitTemplate;

    public void send() {
        String message = "你好现在是 " + new Date() +"";
        System.out.println("leetest---> HelloSender发送内容 : " + message);
        this.rabbitTemplate.setReturnCallback(this);
        this.rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                System.out.println("leetest---> HelloSender消息发送失败" + cause + correlationData.toString());
            } else {
                System.out.println("leetest---> HelloSender 消息发送成功 ");
            }
        });
        this.rabbitTemplate.convertAndSend(RabbitMqConfig.ROUTINGKEY3, message);

        CorrelationData correlationId = new CorrelationData( UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTINGKEY3,
                message, correlationId);
    }
 

 
    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println("leetest---> sender return success" + message.toString()+"==="+i+"==="+s1+"==="+s2);
    }
 
//    @Override
//    public void confirm(CorrelationData correlationData, boolean b, String s) {
//        System.out.println("sender success");
//    }
 
}