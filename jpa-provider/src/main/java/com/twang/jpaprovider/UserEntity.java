package com.twang.jpaprovider;

import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tab_user")
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String email;
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public static UserEntity random(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(RandomStringUtils.randomAlphabetic(10));
        userEntity.setAge(Integer.valueOf(RandomStringUtils.randomNumeric(2)));
        userEntity.setEmail(RandomStringUtils.randomAlphanumeric(20));
        return  userEntity;
    }
}
