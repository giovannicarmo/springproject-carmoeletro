package com.giovannicarmo.projetocurso.carmoeletro.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.giovannicarmo.projetocurso.carmoeletro.domain.CardPayment;
import com.giovannicarmo.projetocurso.carmoeletro.domain.TicketPayment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes(CardPayment.class);
                objectMapper.registerSubtypes(TicketPayment.class);
                super.configure(objectMapper);
            }
        };
        return builder;
    }
}