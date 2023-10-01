package com.triomics.framework.common.utils.spring.context;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class ApplicationContext {

    private static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

    public void run() {
        context.scan("com.triomics.common.utils.spring.context");
    }

    @EnableFeignClients
    @Configuration
    static class ApplicationContextConfig {

        @Bean
        @Primary
        public ObjectMapper objectMapper() {
            ObjectMapper objectMapper =
                    new ObjectMapper()
                            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                            .configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true)
                            .setDateFormat(new StdDateFormat().withColonInTimeZone(true))
                            .setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return objectMapper;
        }

        @Bean
        public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(
                final ObjectMapper objectMapper) {
            return new MappingJackson2HttpMessageConverter(objectMapper);
        }
    }

}
