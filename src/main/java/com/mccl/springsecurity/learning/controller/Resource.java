package com.mccl.springsecurity.learning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Resource {

    @GetMapping("/resource")
    public String resource() {
        return "returning resource";
    }

    @GetMapping("/public")
    public String publicMethod() {
        return "returning public";
    }
}
