package spring.data.pattern.integration.reliable.delivery.controller;

import nyla.solutions.core.patterns.creational.generator.JavaBeanGeneratorCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import showcase.streaming.event.account.domain.Account;
import spring.data.pattern.integration.reliable.delivery.repository.AccountRepository;

import java.net.http.HttpTimeoutException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FlakyAccountControllerTest {

    private FlakyAccountController subject;
    @Mock
    private AccountRepository accountRepository;
    private Account account = JavaBeanGeneratorCreator.of(Account.class).create();

    @BeforeEach
    void setUp() {
        subject = new FlakyAccountController(accountRepository);
    }

    @Test
    void firstTimeTooMany() throws HttpTimeoutException {

        var expected = HttpStatus.TOO_MANY_REQUESTS;
        var actual = subject.timeout(account);

        assertNotNull(actual);

        assertEquals(expected, actual.getStatusCode());


    }

    @Test
    void secondTimeTimeout() throws HttpTimeoutException {

        var expected = HttpStatus.REQUEST_TIMEOUT;
        var actual = subject.timeout(account);
        actual = subject.timeout(account);

        assertNotNull(actual);

        assertEquals(expected, actual.getStatusCode());

    }

    @Test
    void ok() throws HttpTimeoutException {

        account = JavaBeanGeneratorCreator.of(Account.class).create();
        var expected = HttpStatus.OK;
        var actual = subject.timeout(account);

        try {
            actual = subject.timeout(account);
        }
        catch (HttpTimeoutException e)
        {
            actual = new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);

        }

        assertNotNull(actual);

        assertNotEquals(expected, actual.getStatusCode());


    }
}