package org.opendatamesh.platform.adapter.marketplace.executor.starter.service.impl;

import org.opendatamesh.platform.adapter.marketplace.executor.starter.resources.MarketplaceRequestRes;
import org.opendatamesh.platform.adapter.marketplace.executor.starter.resources.MarketplaceResponseRes;
import org.opendatamesh.platform.adapter.marketplace.executor.starter.service.MarketplaceExecutorService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MarketplaceExecutorServiceImpl implements MarketplaceExecutorService {

    @Override
    public MarketplaceResponseRes processRequest(MarketplaceRequestRes request) {
        MarketplaceResponseRes response = new MarketplaceResponseRes();
        response.setCreatedAt(new Date());
        
        // Convert provider details from request to response
        MarketplaceResponseRes.ProviderDetailsRes responseProviderDetails = new MarketplaceResponseRes.ProviderDetailsRes();
        if (request.getRequest() != null && request.getRequest().getProvider() != null) {
            responseProviderDetails.setDataProductFqn(request.getRequest().getProvider().getDataProductFqn());
            responseProviderDetails.setDataProductPortsFqn(request.getRequest().getProvider().getDataProductPortsFqn());
        }
        response.setProviderDetails(responseProviderDetails);

        try {
            // Process the request based on operation type
            switch (request.getOperation()) {
                case "MARKETPLACE_SUBSCRIBE":
                    handleSubscribe(request, response);
                    break;
                case "MARKETPLACE_UNSUBSCRIBE":
                    handleUnsubscribe(request, response);
                    break;
                default:
                    response.setStatus("ERROR");
                    response.setMessage("Unsupported operation: " + request.getOperation());
                    break;
            }
        } catch (Exception e) {
            response.setStatus("ERROR");
            response.setMessage("Error processing request: " + e.getMessage());
        }

        return response;
    }

    private void handleSubscribe(MarketplaceRequestRes request, MarketplaceResponseRes response) {
        // TODO: Implement actual subscription logic
        response.setStatus("SUCCESS");
        response.setMessage("Subscription processed successfully");
    }

    private void handleUnsubscribe(MarketplaceRequestRes request, MarketplaceResponseRes response) {
        // TODO: Implement actual unsubscription logic
        response.setStatus("SUCCESS");
        response.setMessage("Unsubscription processed successfully");
    }
} 