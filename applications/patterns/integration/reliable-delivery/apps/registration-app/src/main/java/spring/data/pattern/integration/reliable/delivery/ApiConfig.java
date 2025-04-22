package spring.data.pattern.integration.reliable.delivery;

import lombok.extern.slf4j.Slf4j;
import nyla.solutions.core.patterns.integration.Publisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import showcase.streaming.event.account.domain.Account;
import spring.data.pattern.integration.reliable.delivery.publisher.AccountHttpPublisher;

import java.net.URI;

@Configuration
@Slf4j
public class ApiConfig {

    @Value("${registration.reliable.uri}")
    private URI reliableUri;

    @Value("${registration.flaky.uri}")
    private URI flakyUri;

    @Bean
    RestTemplate restTemplate()
    {
        return new RestTemplate();
    }

    @Bean("reliable")
    Publisher<Account> reliable(RestTemplate restTemplate){
        return new AccountHttpPublisher(restTemplate,reliableUri);
    }

    @Bean("flaky")
    Publisher<Account> flaky(RestTemplate restTemplate){
        return new AccountHttpPublisher(restTemplate,flakyUri);
    }
}
