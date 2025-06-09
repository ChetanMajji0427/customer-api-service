package com.example.customer;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerApiServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApiServiceApplication.class, args);
    }

    @Bean
    public io.micrometer.core.instrument.MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config().commonTags("application", "CustomerAPI");
    }
}