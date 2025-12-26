package spring.design.data.patterns.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Gregory Green
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("main",p -> p
                        .path("/**")
                        .filters(f -> f.circuitBreaker( c -> c.setName("myCircuitBreaker")
                                .setFallbackUri("forward:/fallback")))
                        .uri("http://localhost:8081"))
                .route("fallback", p -> p
                        .path("/fallback")
                        .uri("http://localhost:8082"))
                .build();
    }
}
