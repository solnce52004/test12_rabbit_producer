package com.example.test12.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    public static final String QUEUE_1 = "queue1";
    public static final String QUEUE_2 = "queue2";

    public static final String DIRECT = "amq.direct";
    public static final String FANOUT = "amq.fanout";
    public static final String TOPIC = "amq.topic";

    @Value("${spring.rabbitmq.host}")
    String host;
    @Value("${spring.rabbitmq.port}")
    int port;
    @Value("${spring.rabbitmq.username}")
    String username;
    @Value("${spring.rabbitmq.password}")
    String password;

    @Bean
    CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);
        return factory;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public AmqpAdmin amqpAdmin(CachingConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(DIRECT);
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TOPIC);
    }
}
