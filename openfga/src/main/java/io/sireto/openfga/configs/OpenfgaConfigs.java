package io.sireto.openfga.configs;

import dev.openfga.sdk.api.client.OpenFgaClient;
import dev.openfga.sdk.api.configuration.ClientConfiguration;
import dev.openfga.sdk.errors.FgaInvalidParameterException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutionException;

@Configuration
public class OpenfgaConfigs {

    @Value("${openfga.api.url}")
    String apiUrl;

    @Value("${openfga.store.id}")
    String storeId;

    @Value("${openfga.authorization.model.id}")
    String authorizationModelId;

    @Bean
    public OpenFgaClient openfgaClient() throws FgaInvalidParameterException, ExecutionException, InterruptedException {
        ClientConfiguration configuration = new ClientConfiguration()
                .apiUrl(apiUrl) // If not specified, will default to "https://localhost:8080"
                .storeId(storeId)
                .authorizationModelId(authorizationModelId); // Optional, can be overridden per request
        OpenFgaClient fgaClient = new OpenFgaClient(configuration);
        return fgaClient;
    }
}
