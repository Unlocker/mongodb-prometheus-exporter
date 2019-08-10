package ru.unlocker.mongodb.prometheus.exporter.model;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.time.Instant;
import java.util.Map;

@Value
@Builder
public class MetricValue {
    private final String name;
    private final double value;
    private final Instant timestamp;
    @Singular
    private Map<String, String> tags;
}
