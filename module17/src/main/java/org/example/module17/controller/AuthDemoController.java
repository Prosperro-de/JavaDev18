package org.example.module17.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AuthDemoController {

//    @GetMapping("/hello")
//    public String hello() {
//        SecurityContext context = SecurityContextHolder.getContext();
//        Authentication authentication = context.getAuthentication();
//        return "Hello, " + authentication.getName();
//    }

    @GetMapping("/hello")
    public String hello(Principal principal) {
        System.out.println("Principal: " + principal.getClass());
        return "Hello, " + principal.getName();
    }
}
