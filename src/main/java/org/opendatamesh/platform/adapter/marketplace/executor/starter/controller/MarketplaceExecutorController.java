package org.opendatamesh.platform.adapter.marketplace.executor.starter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.opendatamesh.platform.adapter.marketplace.executor.starter.resources.MarketplaceRequestRes;
import org.opendatamesh.platform.adapter.marketplace.executor.starter.resources.MarketplaceResponseRes;
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

    @Operation(summary = "Process a marketplace request", description = "Handles subscription and unsubscription requests for data products")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Request processed successfully",
            content = @Content(schema = @Schema(implementation = MarketplaceResponseRes.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/requests")
    public ResponseEntity<MarketplaceResponseRes> processRequest(
            @Parameter(description = "Marketplace request details", required = true)
            @Valid @RequestBody MarketplaceRequestRes request) {
        MarketplaceResponseRes response = marketplaceExecutorService.processRequest(request);
        return ResponseEntity.ok(response);
    }
} 