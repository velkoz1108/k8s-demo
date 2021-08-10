package com.twang.userprovider;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.RandomStringUtils;

public class User {
    private String userName;
    private String email;
    private Integer age;
    private String gender;
    private String address;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public static User random(){
        User user = new User();
        user.setUserName(RandomStringUtils.randomAlphabetic(10));
        user.setAddress(RandomStringUtils.randomAlphabetic(20));
        user.setEmail(RandomStringUtils.randomAlphabetic(15));
        user.setAge(Integer.valueOf(RandomStringUtils.randomNumeric(2)));
        user.setGender(RandomStringUtils.randomAlphabetic(1));
        return  user;
    }
}
