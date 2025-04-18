package spring.data.pattern.filtering.controller;

import spring.data.pattern.filtering.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @Mock
    private AccountRepository repository;
    @Mock
    private ThreadFactory factory = Executors.defaultThreadFactory();
    private String id  = "junit";

    @Test
    void streamEvents() {
        var subject = new AccountController(repository,factory);

        var actual = subject.accounts();

        assertNotNull(actual);

    }
}