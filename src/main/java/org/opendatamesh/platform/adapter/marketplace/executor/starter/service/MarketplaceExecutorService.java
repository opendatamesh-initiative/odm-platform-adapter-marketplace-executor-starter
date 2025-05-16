package org.opendatamesh.platform.adapter.marketplace.executor.starter.service;

import org.opendatamesh.platform.adapter.marketplace.executor.starter.resources.MarketplaceRequestRes;

public interface MarketplaceExecutorService {
    void processRequest(MarketplaceRequestRes request);
} 