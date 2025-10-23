package com.gestaohelio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI gestaoHelioOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestão Hélio Filho Cardans- API REST")
                        .description("Sistema de gestão de serviços para oficina de caminhões.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Henrique Meneg")
                                .email("henriquearraesdev@email.com")
                                .url("https://github.com/henriquearraes"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}
