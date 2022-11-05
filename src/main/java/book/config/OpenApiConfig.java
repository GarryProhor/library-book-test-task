package book.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private final ApplicationConfig config;

    public OpenApiConfig(ApplicationConfig config) {
        this.config = config;
    }

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Book Library API")
                        .description("This is Task Intervale Company")
                        .contact(new Contact().email(config.getEmail()))
                        .license(new License().name(config.getName()))
                        .version(config.getVersion()));
    }
}