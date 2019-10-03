package com.example.springsecurity.Controller;

import com.example.springsecurity.Service.MethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public List<String> hello(){
        List<String> list=new ArrayList<>();
        list.add("hello");
        return list;
    }

    @GetMapping("/admin/hello")
    public String adminHello(){
        return "hello admin";
    }

    @GetMapping("/user/hello")
    public String userHello(){
        return "hello user";
    }

    @GetMapping("/login")
    public String login(){
        return "please login";
    }

    @Autowired
    MethodService methodService;

    @GetMapping("/hello1")
    public String hello1(){
        return methodService.admin();
    }
}
