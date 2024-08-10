package com.example.alert_producer.controller;

import com.example.alert_producer.event.AlertEvent;
import com.example.alert_producer.producer.AlertProducer;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    private static final Logger log = LoggerFactory.getLogger(AlertController.class);
    private final AlertProducer producer;

    public AlertController(AlertProducer producer) {
        this.producer = producer;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AlertEvent sendAlert(@Valid @RequestBody CreateAlertRequest request) {
        var event = new AlertEvent(request.level(), request.message());
        producer.send(event);
        log.info("Alert sent: {}", event);
        return event;
    }
}
