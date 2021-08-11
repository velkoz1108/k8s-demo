package com.twang.userprovider;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping(value = "/")
    public String index() {
        return "This message is from user provider. Token is : " + RandomStringUtils.randomAlphabetic(20);
    }

    @Autowired
    private Environment environment;

    @GetMapping(value = "/springEnv/{name}")
    public String index(@PathVariable String name) {
        return name + " --> " + environment.getProperty(name);
    }

    @Autowired
    private JpaFeignService jpaFeignService;


    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String queryUsers(@PathVariable Long id) {
        return jpaFeignService.selectById(id);
    }

    @GetMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addUser() {
        return jpaFeignService.save();
    }

    @GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long countUsers() {
        return jpaFeignService.count();
    }

    @GetMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String countUsers(@PathVariable Long id) {
        return jpaFeignService.deleteById(id);
    }

    @Autowired
    private BookFeignService bookFeignService;

    @GetMapping(value = "/penname/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String queryPenName(@PathVariable String userName) {
        return bookFeignService.queryPenName(userName);
    }

    @GetMapping(value = "/env/{hostname}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String queryBookProviderEnv(@PathVariable String hostname) {
        return "user provider -> " + hostname + " : " + System.getenv(hostname) + ",book provider -> " + bookFeignService.queryEnv(hostname);
    }
}
