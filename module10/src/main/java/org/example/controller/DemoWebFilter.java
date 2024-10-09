package org.example.controller;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//@WebFilter(value = "*")
public class DemoWebFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader("Authorization");
        if ("111".equals(header)) {
            chain.doFilter(req, res);
        } else {
            res.setStatus(401);
            res.setContentType("application/json");
            res.getWriter().write("{\"Error\": \"Not authorized\"}");
            res.getWriter().close();
        }
    }
}
