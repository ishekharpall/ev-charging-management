package com.evplatform.payment_service.event;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ChargingSessionCompletedConsumer {

    @KafkaListener(
            topics = "charging-session-completed",
            groupId = "payment-service"
    )
    public void consume(String message) {

        System.out.println(
                "Received Kafka message: " + message
        );
    }
}