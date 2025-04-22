package spring.data.pattern.integration.reliable.delivery.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nyla.solutions.core.exception.CommunicationException;
import nyla.solutions.core.patterns.integration.Publisher;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import showcase.streaming.event.account.domain.Account;

import java.net.URI;

@RequiredArgsConstructor
@Slf4j
public class AccountHttpPublisher implements Publisher<Account> {
    private final RestTemplate restTemplate;
    private final URI url;

    @Override
    public void send(Account account) {
        log.info("Account: {}", account);

        var requestHttp = new HttpHeaders();
        requestHttp.setContentType(MediaType.APPLICATION_JSON);

        var requestEntity = new HttpEntity<Account>(account, requestHttp);
        var response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        if (response.getStatusCode().isError())
            throw new CommunicationException(response.toString());
    }
}
