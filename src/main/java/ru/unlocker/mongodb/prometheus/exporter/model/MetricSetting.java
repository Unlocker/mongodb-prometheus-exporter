package ru.unlocker.mongodb.prometheus.exporter.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MetricSetting {
    private QueryType queryType;
    private String collection;
    private List<Map<String, Object>> query;
    private Map<String, Object> projection;
    private List<MetricMapping> metrics;
    private List<MetricMapping> tags;
    private String metricTimestamp;
    private String fetchEvery;
}
