package com.ayusshverma.discord_message_store.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@ConfigurationProperties(prefix = "discord-message-store")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppConfig {
    private String apiBaseUrl;
}
