package org.opendatamesh.platform.adapter.marketplace.executor.starter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MarketplaceServiceConfig {
    @Value("${odm.productPlane.marketplaceService.address}")
    private String address;

    @Value("${odm.productPlane.marketplaceService.active}")
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
} 