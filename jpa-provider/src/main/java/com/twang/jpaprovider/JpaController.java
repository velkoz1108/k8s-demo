package com.twang.jpaprovider;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JpaController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String index() {
        return "welcome";
    }

    @GetMapping("/save")
    public String save() {
        UserEntity user = userRepository.save(UserEntity.random());
        return JSON.toJSONString(user);
    }

    @GetMapping("/select/{id}")
    public String selectById(@PathVariable Long id) {
        UserEntity user = userRepository.getById(id);
        return JSON.toJSONString(user);
    }

    @GetMapping("/count")
    public Long count() {
        return userRepository.count();
    }

    @Transactional
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "success";
    }
}
