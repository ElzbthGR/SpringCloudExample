package ru.kpfu.itis.services;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.services.dto.UserDto;
import ru.kpfu.itis.services.services.UserService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@EnableRabbit
public class Reciever {

    private static final Logger logger = LoggerFactory.getLogger(Reciever.class);

    @Autowired
    UserService service;

    @RabbitListener(queues = "reg-queue")
    public void process(String userDtoString) throws JSONException {

        logger.info("Get from RegistryService: " + userDtoString);

        JSONObject userJson = new JSONObject(userDtoString);
        UserDto userDto = UserDto.builder()
                .name(userJson.getString("name"))
                .url(userJson.getString("url"))
                .build();

        this.service.addUser(userDto);
//
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.submit(() -> this.service.send());
        //service.send();


        logger.info("Good");

    }

}
