/*
 *
 *  * Copyright 2023 VMware, Inc.
 *  * SPDX-License-Identifier: GPL-3.0
 *
 */

package spring.data.pattern.filtering.controller;

import spring.data.pattern.filtering.repository.AccountRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import showcase.streaming.event.account.domain.Account;

import java.time.Duration;
import java.util.concurrent.ThreadFactory;

/**
 * CustomerFavoritesReactiveController
 *
 * @author Gregory Green
 */
@RestController
@RequestMapping(value = "account", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
@Tag(name = "manual", description = "the manual API")
@Slf4j
public class AccountController
{
    @Value("${app.web.refresh.rateSeconds}")
    private long refreshRateSecs = 5;

    private final AccountRepository repository;
    private final ThreadFactory factory;


    public AccountController(AccountRepository repository,
                             @Qualifier("webSocketThreadFactory")
                                       ThreadFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    @GetMapping("accounts")
    public Flux<ServerSentEvent<Iterable<Account>>> accounts() {
        var scheduler = Schedulers.newParallel(5,factory);
        return Flux.interval(Duration.ofSeconds(refreshRateSecs),scheduler)
                .map(sequence -> ServerSentEvent.<Iterable<Account>> builder()
                        .data(repository.findAll())
                        .build());
    }
}