package org.opendatamesh.platform.adapter.marketplace.executor.starter.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI marketplaceExecutorOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ODM Platform Adapter Marketplace Executor API")
                        .description("API for handling marketplace subscription and unsubscription requests")
                        .version("v1.0.0")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
} 