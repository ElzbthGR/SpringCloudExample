package ru.kpfu.itis.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.services.controllers.MessageController;
import ru.kpfu.itis.services.controllers.RegistryController;
import ru.kpfu.itis.services.services.RegistryService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@EnableRabbit
public class Reciever {

    private static final Logger logger = LoggerFactory.getLogger(Reciever.class);

    @Autowired
    private MessageController messageController;

    private String catUrl;

    @RabbitListener(queues = "web-queue")
    public void process(String url) {

        logger.info("Get from CatService: " + url);

        catUrl = url;

        try {
            messageController.sendMsg(url);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public String getCatUrl() {
        return catUrl;
    }
}
