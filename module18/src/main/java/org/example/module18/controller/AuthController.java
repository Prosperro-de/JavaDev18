package org.example.module18.controller;

import lombok.RequiredArgsConstructor;
import org.example.module18.model.dto.request.CustomerCreateRequest;
import org.example.module18.model.dto.request.UserLoginRequest;
import org.example.module18.service.UserService;
import org.example.module18.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public String signup(@RequestBody CustomerCreateRequest request) {
        return userService.createUser(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.userName(), request.password())
//        );
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.userName());
        return jwtUtil.generateToken(userDetails);
    }
}
