package ru.kpfu.itis.services.application;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import org.xmlpull.v1.XmlPullParserException;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;

@SpringBootApplication
@ComponentScan(basePackages = "ru.kpfu.itis.services")
@EnableEurekaClient
@EnableSwagger2
public class App {

    private final String webQueue = "web-queue";
    private final String catQueue = "cat-queue";
    private final String regQueue = "reg-queue";

    @Bean
    public Queue userFormQueue(){
        return new Queue(this.webQueue, true, false, false);
    }

    @Bean
    public Queue urlQueue(){
        return new Queue(this.catQueue, true, false, false);
    }

    @Bean
    public Queue regQueue(){
        return new Queue(this.regQueue, true, false, false);
    }

    @Bean
    public TopicExchange exchange(){
        String topicExchangeName = "my-exchange";
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    public Binding formBinding(TopicExchange exchange){
        String formBinding = "to-web-service";
        return BindingBuilder.bind(userFormQueue()).to(exchange).with(formBinding);
    }

    @Bean
    public Binding urlBinding(TopicExchange exchange){
        String urlBinding = "to-cat-service";
        return BindingBuilder.bind(urlQueue()).to(exchange).with(urlBinding);
    }

    @Bean
    public Binding regBinding(TopicExchange exchange){
        String regBinding = "to-reg-service";
        return BindingBuilder.bind(regQueue()).to(exchange).with(regBinding);
    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(this.webQueue);
        container.setQueueNames(this.catQueue);
        container.setQueueNames(this.regQueue);
        return container;
    }

    @Bean
    public Docket api() throws IOException, XmlPullParserException {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.kpfu.itis.services.controllers"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
