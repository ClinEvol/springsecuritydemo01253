package com.example.springsecuritydemo0125.demo;

import org.springframework.util.DigestUtils;

public class DemoMD5 {
    public static void main(String[] args) {
        //明文   123456
        //密文   e10adc3949ba59abbe56e057f20f883e
        String md5Password = DigestUtils.md5DigestAsHex("java@#1234 ".getBytes());
        System.out.println(md5Password);


    }
}
