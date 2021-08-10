package com.twang.userprovider;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "jpa-provider", url = "${jpa-provider.url}")
public interface JpaFeignService {

    @GetMapping("/")
    String index();

    @GetMapping("/save")
    String save();

    @GetMapping("/select/{id}")
    String selectById(@PathVariable Long id);

    @GetMapping("/count")
    Long count();

    @GetMapping("/delete/{id}")
    String deleteById(@PathVariable Long id);

}
