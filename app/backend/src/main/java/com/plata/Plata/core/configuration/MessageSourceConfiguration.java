package com.plata.Plata.core.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;

@Configuration
public class MessageSourceConfiguration {
    public static final String RESOURCE_BUNDLE_BASE_NAME = "messages";

    @Bean
    public MessageSource messageSource() {
        var resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename(RESOURCE_BUNDLE_BASE_NAME);
        resourceBundleMessageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());

        return resourceBundleMessageSource;
    }

}
