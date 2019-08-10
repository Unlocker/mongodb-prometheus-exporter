package ru.unlocker.mongodb.prometheus.exporter.web.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/metrics")
@RequiredArgsConstructor
public class PromMetricsController {

    private final CollectorRegistry collectorRegistry;

    @GetMapping
    public ResponseEntity<String> metrics() throws IOException {
        try (Writer writer = new StringWriter()) {
            TextFormat.write004(writer, collectorRegistry.metricFamilySamples());
            writer.flush();
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE, TextFormat.CONTENT_TYPE_004);
            return new ResponseEntity<>(writer.toString(), headers, HttpStatus.OK);
        }
    }

}
