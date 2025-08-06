package com.example.springcase.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class dmmm {

    @GetMapping("/")
    public String hello() {
        return "✅ Spring Boot is working!";
    }
}
