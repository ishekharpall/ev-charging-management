package com.evplatform.charging_service.event;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ChargingSessionEventProducer {

    public static final String TOPIC =
            "charging-session-completed";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ChargingSessionEventProducer(
            KafkaTemplate<String, String> kafkaTemplate
    ) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(ChargingSessionCompletedEvent event) {

        String message =
                "Charging session completed: "
                        + event.chargingSessionId()
                        + ", energy: "
                        + event.energyConsumedKwh()
                        + " kWh";

        kafkaTemplate.send(
                TOPIC,
                event.chargingSessionId().toString(),
                message
        );
    }
}