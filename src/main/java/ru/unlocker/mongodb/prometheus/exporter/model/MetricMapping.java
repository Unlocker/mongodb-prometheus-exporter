package ru.unlocker.mongodb.prometheus.exporter.model;

import lombok.Data;

@Data
public class MetricMapping {
    private String source;
    private String target;
}
