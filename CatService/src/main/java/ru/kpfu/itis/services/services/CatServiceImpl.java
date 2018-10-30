package ru.kpfu.itis.services.services;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.kpfu.itis.services.dto.CatDto;

@Service
public class CatServiceImpl implements CatService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final String exchangeTopic = "my-exchange";
    private final String formBinding = "to-web-service";

    @Value("${cats.get.request.url}")
    private String catsGetRequestUrl;

    @Override
    public CatDto getCat() {
        return restTemplate.getForEntity(catsGetRequestUrl, CatDto[].class).getBody()[0];
    }

    @Override
    public void send() {
        CatDto cat = this.getCat();
        this.rabbitTemplate.convertAndSend(this.exchangeTopic, this.formBinding, cat.getUrl());
    }
}