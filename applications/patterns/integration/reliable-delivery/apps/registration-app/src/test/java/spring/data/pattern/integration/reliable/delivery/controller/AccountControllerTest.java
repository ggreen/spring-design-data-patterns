package spring.data.pattern.integration.reliable.delivery.controller;

import nyla.solutions.core.patterns.creational.generator.JavaBeanGeneratorCreator;
import nyla.solutions.core.patterns.integration.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import showcase.streaming.event.account.domain.Account;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    private Account account = JavaBeanGeneratorCreator.of(Account.class).create();
    private AccountController subject;

    @Mock
    private Publisher<Account> flaky;

    @Mock
    private Publisher<Account> reliable;

    @BeforeEach
    void setUp() {
        subject = new AccountController(flaky,reliable);
    }

    @Test
    void flaky() {

        subject.publishFlaky(account);

        verify(flaky).send(account);
    }

    @Test
    void reliable() {

        subject.publishReliable(account);

        verify(reliable).send(account);
    }
}