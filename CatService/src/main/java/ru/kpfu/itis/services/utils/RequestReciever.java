package ru.kpfu.itis.services.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.services.services.CatService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@EnableRabbit
@Component
public class RequestReciever {

    private static final Logger logger = LoggerFactory.getLogger(RequestReciever.class);

    @Autowired
    CatService service;

    @RabbitListener(queues = "cat-queue")
    public void process(String message){

        logger.info("Get from RegistryService: " + message);

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> this.service.send());
        //service.send();

        logger.info("Send back");

    }
}
