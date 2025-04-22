package spring.data.pattern.integration.reliable.delivery.publisher;

import nyla.solutions.core.patterns.creational.generator.JavaBeanGeneratorCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import showcase.streaming.event.account.domain.Account;

import java.net.URI;
import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountHttpPublisherTest {

    private Account account = JavaBeanGeneratorCreator.of(Account.class).create();
    private AccountHttpPublisher subject;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ResponseEntity requestEntity;
    private HttpStatusCode statusCode = HttpStatus.OK;

    @BeforeEach
    void setUp() throws URISyntaxException {
        URI uri = new URI("http://localhost");
        subject = new AccountHttpPublisher(restTemplate,uri);
    }

    @Test
    void pubAccount() {
        when(restTemplate.exchange(any(),any(),any(),any(Class.class))).thenReturn(requestEntity);
        when(requestEntity.getStatusCode()).thenReturn(statusCode);
        subject.send(account);

        verify(requestEntity).getStatusCode();
    }
}