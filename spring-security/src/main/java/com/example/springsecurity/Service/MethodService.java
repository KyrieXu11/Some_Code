package com.example.springsecurity.Service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class MethodService {

    @PreAuthorize("hasRole('admin')")
    public String admin(){
        return "hello admin";
    }

    @Secured("ROLE_user")
    public String user(){
        return "hello user";
    }

    @PreAuthorize("hasAnyAuthority('admin','user')")
    public String hello(){
        return "hello hello";
    }
}
