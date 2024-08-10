package com.example.alert_producer.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateAlertRequest (@Min(1) int level, @NotBlank String message) {
}
