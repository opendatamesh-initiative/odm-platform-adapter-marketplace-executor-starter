package org.opendatamesh.platform.adapter.marketplace.executor.starter.resources;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "Provider information for the request")
public class ProviderRes {
    @Schema(description = "Data product fully qualified name", required = true)
    @NotBlank(message = "Data product FQN is required")
    private String dataProductFqn;

    @Schema(description = "Data product ports fully qualified names", required = true)
    @NotNull(message = "Data product ports FQNs are required")
    private List<String> dataProductPortsFqn;

    public String getDataProductFqn() {
        return dataProductFqn;
    }

    public void setDataProductFqn(String dataProductFqn) {
        this.dataProductFqn = dataProductFqn;
    }

    public List<String> getDataProductPortsFqn() {
        return dataProductPortsFqn;
    }

    public void setDataProductPortsFqn(List<String> dataProductPortsFqn) {
        this.dataProductPortsFqn = dataProductPortsFqn;
    }
} 