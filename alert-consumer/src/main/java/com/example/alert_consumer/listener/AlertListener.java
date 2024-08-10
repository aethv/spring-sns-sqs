package com.example.alert_consumer.listener;

import com.example.alert_consumer.event.AlertEvent;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class AlertListener {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(AlertListener.class);

    @SqsListener("${aws.sqs.destination}")
    public void handleEvent(AlertEvent event) {
        log.info("Received event: {}", event);
    }
}
