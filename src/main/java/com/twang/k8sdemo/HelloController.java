package com.twang.k8sdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloController {

    @Value("${appVersion}")
    private String appVersion;

    @GetMapping("/")
    public String getHostname() {
        return "Hello, appVersion is : " + appVersion + " . Time is : " + new Date();
    }

    @GetMapping("/{hostname}")
    public String getHostname(@PathVariable String hostname) {
        return hostname + ":" + System.getenv(hostname.toUpperCase());
    }
}
