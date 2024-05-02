package com.demo.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoMVCController {

    @GetMapping("/hello")
    public String displayMessage() {
        return "hello";
    }
}