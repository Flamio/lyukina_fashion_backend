package com.mmenshikov.lyukinafashion.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security")
@Data
public class SecurityProperties {

    private Admin admin;

    @Data
    public static class Admin {
        private String login;
        private String password;
    }
}
