package org.opendatamesh.platform.adapter.marketplace.executor.starter.service;

import org.opendatamesh.platform.adapter.marketplace.executor.starter.resources.MarketplaceRequestRes;
import org.opendatamesh.platform.adapter.marketplace.executor.starter.resources.MarketplaceResponseRes;

public interface MarketplaceExecutorService {
    MarketplaceResponseRes processRequest(MarketplaceRequestRes request);
} 