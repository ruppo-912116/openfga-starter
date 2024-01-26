package io.sireto.openfga.configs;

import dev.openfga.sdk.api.model.CreateStoreRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class CreateOpenfgiStore {
    @Value("${fga.api.scheme}")
    String apiScheme;

    @Value("${fga.api.host}")
    String apiHost;

    @Bean
    public CommandLineRunner createStore() {
        return args -> {
            WebClient client = WebClient.builder()
                    .baseUrl(apiScheme + "://" + apiHost)
                    .build();

            CreateStoreRequest request = new CreateStoreRequest();
            request.setName("pdfeditor");

            client.method(HttpMethod.POST)
                    .uri("/stores")
                    .body(BodyInserters.fromValue(request))
                    .retrieve()
                    .bodyToMono(String.class)
                    .doOnError(Throwable::printStackTrace)
                    .subscribe(response -> System.out.println("Response: " + response));
        };
    }
}
