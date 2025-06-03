package org.opendatamesh.platform.adapter.marketplace.executor.starter.service;

import org.opendatamesh.platform.adapter.marketplace.executor.starter.config.MarketplaceServiceConfig;
import org.opendatamesh.platform.adapter.marketplace.executor.starter.exception.BadRequestException;
import org.opendatamesh.platform.adapter.marketplace.executor.starter.resources.MarketplaceRequestRes;
import org.opendatamesh.platform.adapter.marketplace.executor.starter.resources.MarketplaceRequestStatus;
import org.opendatamesh.platform.adapter.marketplace.executor.starter.resources.MarketplaceResponseRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class MarketplaceExecutorService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final MarketplaceServiceConfig marketplaceServiceConfig;
    private final RestTemplate restTemplate;

    @Autowired
    @Lazy
    private MarketplaceExecutorService self;

    @Autowired
    public MarketplaceExecutorService(MarketplaceServiceConfig marketplaceServiceConfig) {
        this.marketplaceServiceConfig = marketplaceServiceConfig;
        this.restTemplate = new RestTemplate();
    }

    public void processRequest(MarketplaceRequestRes request) {
        // Validate request before processing
        validateRequest(request);

        // Process request asynchronously
        self.processRequestAsync(request);
    }

    @Async
    public void processRequestAsync(MarketplaceRequestRes request) {
        try {
            // Process the request based on operation type
            switch (request.getOperation()) {
                case "MARKETPLACE_SUBSCRIBE":
                    handleSubscribe(request);
                    break;
                case "MARKETPLACE_UNSUBSCRIBE":
                    handleUnsubscribe(request);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported operation: " + request.getOperation());
            }
        } catch (Exception e) {
            // Send error response
            sendResponse(createErrorResponse(request, e.getMessage()), request.getRequest().getIdentifier());
            log.error(e.getMessage(), e);
        }
    }

    private void validateRequest(MarketplaceRequestRes request) {
        if (request.getRequest() == null) {
            throw new BadRequestException("Request details are required");
        }

        if (request.getOperation() == null || request.getOperation().trim().isEmpty()) {
            throw new BadRequestException("Operation type is required");
        }

        if (!request.getOperation().equals("MARKETPLACE_SUBSCRIBE") &&
                !request.getOperation().equals("MARKETPLACE_UNSUBSCRIBE")) {
            throw new BadRequestException("Invalid operation type. Must be either MARKETPLACE_SUBSCRIBE or MARKETPLACE_UNSUBSCRIBE");
        }

        if (request.getRequest().getStartDate() != null && request.getRequest().getEndDate() != null && request.getRequest().getStartDate().after(request.getRequest().getEndDate())) {
            throw new BadRequestException("Start date must be before end date");
        }
    }

    private void handleSubscribe(MarketplaceRequestRes request) {
        // TODO: Implement actual subscription logic
        // This will be executed asynchronously

        // Send success response
        sendResponse(createSuccessResponse(request, "Subscription processed successfully"), request.getRequest().getIdentifier());
    }

    private void handleUnsubscribe(MarketplaceRequestRes request) {
        // TODO: Implement actual unsubscription logic
        // This will be executed asynchronously

        // Send success response
        sendResponse(createSuccessResponse(request, "Unsubscription processed successfully"), request.getRequest().getIdentifier());
    }

    private MarketplaceResponseRes createSuccessResponse(MarketplaceRequestRes request, String message) {
        MarketplaceResponseRes response = new MarketplaceResponseRes();
        response.setStatus(MarketplaceRequestStatus.GRANTED);
        response.setMessage(message);
        response.setProvider(request.getRequest().getProvider());
        response.setCreatedAt(new Date());
        return response;
    }

    private MarketplaceResponseRes createErrorResponse(MarketplaceRequestRes request, String message) {
        MarketplaceResponseRes response = new MarketplaceResponseRes();
        response.setStatus(MarketplaceRequestStatus.ERROR);
        response.setMessage(message);
        response.setProvider(request.getRequest().getProvider());
        response.setCreatedAt(new Date());
        return response;
    }

    private void sendResponse(MarketplaceResponseRes response, String requestIdentifier) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<MarketplaceResponseRes> entity = new HttpEntity<>(response, headers);
            restTemplate.postForEntity(
                    marketplaceServiceConfig.getAddress() + "/api/v1/pp/marketplace/requests/{identifier}/results",
                    entity,
                    Void.class,
                    requestIdentifier
            );
        } catch (RestClientException e) {
            log.warn(e.getMessage(), e);
        }
    }
} 