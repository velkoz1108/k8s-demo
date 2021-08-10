package com.twang.bookprovider;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @GetMapping("/")
    public String index() {
        return "Hello from " +System.getenv("HOSTNAME");
    }

    @GetMapping("/penName")
    public String queryPenName(@RequestParam String userName) {
        return "Hi " + userName + ", your pen name is : " + RandomStringUtils.randomAlphabetic(5, 10);
    }

    @GetMapping("/env/{hostname}")
    public String queryEnv(@PathVariable String hostname) {
        return hostname + " : " + System.getenv(hostname);
    }
}
