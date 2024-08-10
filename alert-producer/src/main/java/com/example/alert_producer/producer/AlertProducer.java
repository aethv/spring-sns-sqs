package com.example.alert_producer.producer;

import com.example.alert_producer.event.AlertEvent;
import io.awspring.cloud.sns.core.SnsTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AlertProducer {

    private final SnsTemplate snsTemplate;

    public AlertProducer(SnsTemplate snsTemplate) {
        this.snsTemplate = snsTemplate;
    }

    @Value("${aws.sns.destination}")
    private String destination;

    public void send(AlertEvent event) {
        snsTemplate.convertAndSend(destination, event);
    }
}
