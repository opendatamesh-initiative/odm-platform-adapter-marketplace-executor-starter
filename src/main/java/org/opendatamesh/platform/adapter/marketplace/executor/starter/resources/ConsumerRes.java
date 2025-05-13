package org.opendatamesh.platform.adapter.marketplace.executor.starter.resources;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Schema(description = "Consumer information for the request")
public class ConsumerRes {
    @Schema(description = "Consumer type (dataproduct, user, team)", required = true)
    @NotBlank(message = "Consumer type is required")
    private String type;

    @Schema(description = "Consumer identifier (fqn, email, etc.)", required = true)
    @NotBlank(message = "Consumer identifier is required")
    private String identifier;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
} 