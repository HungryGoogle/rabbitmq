package com.example.rabbitmq.controller;

import com.example.rabbitmq.sender.FirstSender;
import com.example.rabbitmq.sender.HelloSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 */
@RestController
public class SendController {

    @Autowired
    private FirstSender firstSender;

    @Autowired
    private HelloSender helloSender;

    @GetMapping("/hello")
    public String hello(String message){
        return "hello";
    }

    @GetMapping("/send")
    public String send(){
        String uuid = UUID.randomUUID().toString();
        String message = "hellodddd";

        firstSender.send(uuid,message);
        return uuid;
    }

    @GetMapping("/send_ack")
    public String sendAck(){
        helloSender.send();
        return "send_ack";
    }
}
