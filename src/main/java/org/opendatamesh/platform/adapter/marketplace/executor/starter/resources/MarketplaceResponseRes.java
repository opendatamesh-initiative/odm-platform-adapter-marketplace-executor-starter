package org.opendatamesh.platform.adapter.marketplace.executor.starter.resources;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

@Schema(description = "Response for marketplace operations")
public class MarketplaceResponseRes {
    @Schema(description = "Status of the operation (SUCCESS or ERROR)", example = "SUCCESS")
    private String status;

    @Schema(description = "Message describing the operation result", example = "Subscription processed successfully")
    private String message;

    @Schema(description = "Provider information in the response")
    private ProviderRes provider;

    @Schema(description = "Timestamp when the response was created")
    private Date createdAt;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ProviderRes getProvider() {
        return provider;
    }

    public void setProvider(ProviderRes provider) {
        this.provider = provider;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
} 