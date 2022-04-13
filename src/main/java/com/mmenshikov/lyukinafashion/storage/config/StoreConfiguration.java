package com.mmenshikov.lyukinafashion.storage.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "data-store")
@Data
@Accessors(chain = true)
public class StoreConfiguration {
    private String path;
}
