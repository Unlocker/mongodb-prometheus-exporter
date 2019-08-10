package ru.unlocker.mongodb.prometheus.exporter.config;

import io.prometheus.client.CollectorRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableWebFlux
public class ExporterConfig {

    @Bean
    public CollectorRegistry collectorRegistry() {
        return CollectorRegistry.defaultRegistry;
    }
}
