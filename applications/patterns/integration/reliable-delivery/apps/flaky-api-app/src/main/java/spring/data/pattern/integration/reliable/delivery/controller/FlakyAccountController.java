package spring.data.pattern.integration.reliable.delivery.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import showcase.streaming.event.account.domain.Account;
import spring.data.pattern.integration.reliable.delivery.repository.AccountRepository;

import java.net.http.HttpTimeoutException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.HashMap;

import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping("flaky")
@RequiredArgsConstructor
@Slf4j
public class FlakyAccountController {

    private final AccountRepository accountRepository;
    private HashMap<String,Boolean> map = new HashMap<>();

    @PostMapping("account")
    public ResponseEntity<Account> timeout(@RequestBody Account account) throws HttpTimeoutException {

        if(account == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        //first return error
        if( !map.containsKey(account.getId())) {
            map.put(account.getId(),Boolean.TRUE);
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }

        //Throw
        if ("TIMEOUT".equals(account.getStatus()))
        {
            log.error("RETURN timeout error based on status for account : {}",account);
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build();
        }

        int second = LocalDateTime.now().get(ChronoField.SECOND_OF_DAY);
        if(second %2 != 0)
        {
            var timeoutException =new HttpTimeoutException("TESTING");
            log.error("Cannot process account: % THROWING EXCEPTION: %",account, timeoutException);
            throw timeoutException;
        }

        accountRepository.save(account);
        var ok = ok(account);
        log.info("GOOD REPLAY: {}",ok);
        return ok;
    }
}
