package com.datastax.demo.stargate.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiDocumentationConfig {

    @Bean
    public OpenAPI openApiSpec(@Value("1.3.9") String appVersion) {
        String des = "Api for User Interface";
        Info info  = new Info()
                .title("Stargate Demo")
                .version(appVersion)
                .description(des)
                .termsOfService("http://swagger.io/terms/")
                .license(new License().name("Apache 2.0").url("http://springdoc.org"));
        return new OpenAPI().addServersItem(new Server().url("/")).info(info);
    }

}
