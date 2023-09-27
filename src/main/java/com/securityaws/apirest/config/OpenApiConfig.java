package com.securityaws.apirest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig { // classe de configuração do swagger

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("RESTfull api width java 17 and Spring Boot 3")
                        .description("Criando uma api restfull com springboot e usando docker + aws")
                        .version("v1")
                        .termsOfService("https://www.linkedin.com/in/carlos-sousa-26b4b5212/")
                        .contact(new Contact().email("carlosramos10k@gmail.com"))
                );
    }
}
