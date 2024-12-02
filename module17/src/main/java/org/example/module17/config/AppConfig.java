package org.example.module17.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

    @Bean(name = "paymentClient")
    public RestTemplate getPaymentClient(RestTemplateBuilder builder) {
        return builder
                .rootUri("http://localhost:8081/")
                .setConnectTimeout(Duration.ofSeconds(10))
                .build();
    }

    @Bean(name = "productClient")
//    @Primary
    public RestTemplate getProductClient(RestTemplateBuilder builder) {
        return builder
                .rootUri("http://localhost:8082/")
                .setConnectTimeout(Duration.ofSeconds(5))
                .build();
    }
}
