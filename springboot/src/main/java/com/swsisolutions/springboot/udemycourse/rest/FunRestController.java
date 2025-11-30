package com.swsisolutions.springboot.udemycourse.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
    // expose /hello return Hello Spring Boot
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello Spring Boot";
    }

    @GetMapping("/hi1")
    public String sayHi() {
        return "Hi Spring Boot";
    }
}
