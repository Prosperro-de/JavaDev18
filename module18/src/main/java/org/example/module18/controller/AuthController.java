package org.example.module18.controller;

import lombok.RequiredArgsConstructor;
import org.example.module18.model.dto.request.CustomerCreateRequest;
import org.example.module18.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

//    @GetMapping("/hello")
//    public String hello() {
//        SecurityContext context = SecurityContextHolder.getContext();
//        Authentication authentication = context.getAuthentication();
//        return "Hello, " + authentication.getName();
//    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public String signup(@RequestBody CustomerCreateRequest request) {
        return userService.createUser(request);
    }
}
