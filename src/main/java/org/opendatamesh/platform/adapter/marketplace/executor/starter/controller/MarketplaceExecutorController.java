package org.opendatamesh.platform.adapter.marketplace.executor.starter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.opendatamesh.platform.adapter.marketplace.executor.starter.resources.MarketplaceRequestRes;
import org.opendatamesh.platform.adapter.marketplace.executor.starter.service.MarketplaceExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/up/marketplace-executor")
@Tag(name = "Marketplace Executor", description = "API for handling marketplace subscription and unsubscription requests")
public class MarketplaceExecutorController {
    private final MarketplaceExecutorService marketplaceExecutorService;

    @Autowired
    public MarketplaceExecutorController(MarketplaceExecutorService marketplaceExecutorService) {
        this.marketplaceExecutorService = marketplaceExecutorService;
    }

    @Operation(summary = "Process a marketplace request", description = "Handles subscription and unsubscription requests for data products asynchronously")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Request accepted for processing"),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/requests")
    public ResponseEntity<Void> processRequest(
            @Parameter(description = "Marketplace request details", required = true)
            @Valid @RequestBody MarketplaceRequestRes request) {
        marketplaceExecutorService.processRequest(request);
        return ResponseEntity.accepted().build();
    }
} 