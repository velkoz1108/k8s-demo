package com.twang.userprovider;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "book-provider", url = "${book-provider.url}")
public interface BookFeignService {

    @GetMapping("/penName")
    String queryPenName(@RequestParam String userName);

    @GetMapping("/env/{hostname}")
    String queryEnv(@PathVariable String hostname);
}
