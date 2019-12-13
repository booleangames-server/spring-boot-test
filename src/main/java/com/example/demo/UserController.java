package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@RestController
public class UserController {
    
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{platformType}/{siteUserId}")
    public Mono<User> getUser(@PathVariable("platformType") int platformType, @PathVariable("siteUserId") String siteUserId) {
        log.info("platformType : " + platformType + " siteUserId : " + siteUserId);

        Mono<User> user = Mono.fromCallable(() -> userService.getUser(platformType, siteUserId))
            .subscribeOn(Schedulers.elastic())
            .log();
        
        return user;
    }

    @GetMapping("/users/{asn}")
    public Mono<User> getUser(@PathVariable("asn") long asn) {
        log.info("asn : " + asn);

        Mono<User> user = Mono.fromCallable(() -> userService.getUser(asn))
            .subscribeOn(Schedulers.elastic())
            .log();

        return user;
    }
}