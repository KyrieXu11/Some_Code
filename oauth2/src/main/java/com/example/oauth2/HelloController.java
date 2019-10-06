package com.example.oauth2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/admin/hello")
    public String helloadmin(){
        return "hello admin";
    }

    @GetMapping("/user/hello")
    public String hellouser(){
        return "hello user";
    }
}