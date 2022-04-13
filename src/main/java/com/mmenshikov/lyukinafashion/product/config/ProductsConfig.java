package com.mmenshikov.lyukinafashion.product.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "products")
@Data
@Accessors(chain = true)
public class ProductsConfig {
    private Integer itemsOnPage;
}
