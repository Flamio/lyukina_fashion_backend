

package com.mmenshikov.lyukinafashion.admin.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI customOpenApi(@Value("${swagger.server-url}") String serverUrl) {
    return new OpenAPI().info(new Info().title("Application API")
            .version("1.0")
            .description("lyukina fashion store")
            .license(new License().name("Apache 2.0")
                .url("http://springdoc.org"))
            .contact(new Contact().name("mmenshikov")
                .email("maksim-menshikov@mail.ru")))
        .servers(List.of(new Server().url(serverUrl)
            .description("server")));
  }
}
