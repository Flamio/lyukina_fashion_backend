package com.mmenshikov.lyukinafashion.notification.properties;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Accessors(chain = true)
@Configuration
@ConfigurationProperties(prefix = "notification")
public class NotificationProperties {
    private String emailFrom;
}
