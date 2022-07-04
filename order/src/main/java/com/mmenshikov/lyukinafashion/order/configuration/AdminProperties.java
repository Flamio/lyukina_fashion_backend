package com.mmenshikov.lyukinafashion.order.configuration;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = "admin")
@Configuration
public class AdminProperties {
    private String email;
}
