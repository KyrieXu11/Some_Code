package com.example.springsecuritydb.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/db/hello")
    public String hellodb(){
        return "hello db";
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
