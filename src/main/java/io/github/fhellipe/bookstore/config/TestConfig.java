package io.github.fhellipe.bookstore.config;

import io.github.fhellipe.bookstore.services.EmailService;
import io.github.fhellipe.bookstore.services.MockMailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Bean
    public EmailService emailService() {
        return new MockMailService();
    }
}
