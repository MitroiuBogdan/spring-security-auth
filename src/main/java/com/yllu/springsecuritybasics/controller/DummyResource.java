package com.yllu.springsecuritybasics.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyResource {


    @GetMapping("/test")
    public String testEndpoint() {
        return "Test";
    }

}
