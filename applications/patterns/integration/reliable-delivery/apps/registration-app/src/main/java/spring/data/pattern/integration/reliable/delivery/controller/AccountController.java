package spring.data.pattern.integration.reliable.delivery.controller;

import lombok.extern.slf4j.Slf4j;
import nyla.solutions.core.patterns.conversion.Converter;
import nyla.solutions.core.patterns.integration.Publisher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import showcase.streaming.event.account.domain.Account;

@RestController
@RequestMapping("accounts")
@Slf4j
public class AccountController {
    private final Publisher<Account> reliable;
    private final Publisher<Account> flaky;
    private Converter<Account,byte[]> converter;

    public AccountController(@Qualifier("flaky") Publisher<Account> flaky, @Qualifier("reliable") Publisher<Account> reliable) {
        this.flaky = flaky;
        this.reliable = reliable;
    }


    @PostMapping("reliable")
    public void publishReliable(@RequestBody Account account) {
        log.info("Publishing reliable Account: {}",account);
        reliable.send(account);
    }

    @PostMapping("flaky")
    public void publishFlaky(@RequestBody Account account) {
        log.info("Publishing flaky Account: {}",account);
        flaky.send(account);
        log.info("Sent");
    }

}
