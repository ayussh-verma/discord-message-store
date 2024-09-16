package com.ayusshverma.discord_message_store.config;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@JsonComponent
@Configuration
public class JacksonConfig {
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer configureDateTimeFormat() {
        return builder -> {
            builder.serializers(new OffsetDateTimeSerializer(OffsetDateTimeSerializer.INSTANCE, false, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT), JsonFormat.Shape.STRING));
        };
    }
}
