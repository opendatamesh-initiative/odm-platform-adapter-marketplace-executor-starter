package org.opendatamesh.platform.adapter.marketplace.executor.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MarketplaceExecutorStarterApplication {
    public static void main(String[] args) {
        SpringApplication.run(MarketplaceExecutorStarterApplication.class, args);
    }
} 