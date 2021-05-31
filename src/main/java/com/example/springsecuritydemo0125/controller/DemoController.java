package com.example.springsecuritydemo0125.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo1")
public class DemoController {

    @RequestMapping("/getuser")
    @PreAuthorize("hasAnyRole('user')")   //在执行这个方法之前验证当前用户有没有这个角色，user角色才能访问
    public String getUser(){
        return "this is my user";
    }

    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping("/getadmin")
    public String getAdmin(){
        return "this is my admin";
    }

}
