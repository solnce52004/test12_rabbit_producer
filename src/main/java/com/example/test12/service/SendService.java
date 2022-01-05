package com.example.test12.service;

import com.example.test12.config.RabbitmqConfig;
import com.example.test12.dto.MessageDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j

public class SendService {
    private final RabbitTemplate rabbitTemplate;
    private static final MessageDto MSG = new MessageDto();

    //    @Scheduled(fixedDelay = 5000)
    public void sendDir() {
        MSG.setId(getTestUUID())
                .setFrom("DIRECT");

        rabbitTemplate.convertAndSend(
                RabbitmqConfig.DIRECT,
                "log.error",
                MSG.setBody("log.error")
        );
        rabbitTemplate.convertAndSend(
                RabbitmqConfig.DIRECT,
                "log.warning",
                MSG.setBody("log.warning")
        );
        rabbitTemplate.convertAndSend(
                RabbitmqConfig.DIRECT,
                "log.info",
                MSG.setBody("log.info")
        );

        log.info("To Queue 1/2 from exch DIRECT: {}", MSG);
    }

    public void sendFan() {
        MSG.setId(getTestUUID())
                .setFrom("FANOUT");

        rabbitTemplate.convertAndSend(
                RabbitmqConfig.FANOUT,
                "FANOUT",
                MSG.setBody("FANOUT")
        );

        log.info("To Queue1 from exch FANOUT: {}", MSG);
    }

    public void sendTop() {
        MSG.setId(getTestUUID())
                .setFrom("TOPIC");

        rabbitTemplate.convertAndSend(
                RabbitmqConfig.TOPIC,
                "log.error",
                MSG.setBody("log.error")
        );
        rabbitTemplate.convertAndSend(
                RabbitmqConfig.TOPIC,
                "log.user.warning",
                MSG.setBody("log.user.warning")
        );
        rabbitTemplate.convertAndSend(
                RabbitmqConfig.TOPIC,
                "log.user.info",
                MSG.setBody("log.user.info")
        );

        log.info("To Queue 1/2 from exch TOPIC: {}", MSG);
    }

    //////

    public void send2(MessageDto msg) {
        rabbitTemplate.convertAndSend(RabbitmqConfig.QUEUE_2, msg);
        log.info("To Queue2 : {}", msg);
    }

    private String getTestUUID() {
        return UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(16);
    }
}
