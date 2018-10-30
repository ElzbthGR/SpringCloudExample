package ru.kpfu.itis.services.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.kpfu.itis.services.Reciever;
import ru.kpfu.itis.services.controllers.MessageController;
import ru.kpfu.itis.services.dto.UserDto;

import java.util.Arrays;
import java.util.List;

@Service
public class RegistryServiceImpl implements RegistryService{

    private static final Logger logger = LoggerFactory.getLogger(RegistryServiceImpl.class);

    private final String exchangeTopic = "my-exchange";
    private final String urlBinding = "to-cat-service";
    private final String regBinding = "to-reg-service";
    //private final String smsBinding = "send-sms";
    private String message = "/cats/search";
    private final String msg = "THANK YOU FOR REGISTRATION";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    MessageController messageController;

    @Override
    public void send() {

        logger.info("Sending to CatService: " + message);

        this.rabbitTemplate.convertAndSend(this.exchangeTopic, this.urlBinding, message);
        //this.rabbitTemplate.convertAndSend(this.exchangeTopic, this.smsBinding, userDTO.getPhone());

        logger.info("It's done.");

    }

    @Override
    public void sendToSave(UserDto userDto) {

        logger.info("Send to RegService: " + userDto.toString());

        this.rabbitTemplate.convertAndSend(this.exchangeTopic, this.regBinding, userDto.toString());

        try {
            this.messageController.sendNotif(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("It's done.");

    }

    @Override
    public List<UserDto> getAll() {
        return Arrays.asList(restTemplate.getForObject("http://REG/get_all", UserDto[].class));
    }
}
