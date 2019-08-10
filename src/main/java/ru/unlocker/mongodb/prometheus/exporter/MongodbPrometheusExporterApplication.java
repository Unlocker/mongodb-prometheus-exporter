package ru.unlocker.mongodb.prometheus.exporter;

import io.prometheus.client.hotspot.DefaultExports;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MongodbPrometheusExporterApplication {

	public static void main(String[] args) {
        DefaultExports.initialize();
		SpringApplication.run(MongodbPrometheusExporterApplication.class, args);
	}

}
