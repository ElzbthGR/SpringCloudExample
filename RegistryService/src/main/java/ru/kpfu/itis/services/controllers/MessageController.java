package ru.kpfu.itis.services.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import ru.kpfu.itis.services.dto.CatDto;
import ru.kpfu.itis.services.dto.Response;

import javax.annotation.PostConstruct;

@Controller
public class MessageController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    public void sendMsg(String s) throws InterruptedException {
        simpMessagingTemplate.convertAndSend("/catSocket", new Response("OK", s));
    }

    public void sendNotif(String s) throws InterruptedException {
        simpMessagingTemplate.convertAndSend("/regSocket", new Response("OK", s));
    }

    @SubscribeMapping("/log")
    public void subscription() {

    }

}