package com.maidservice.maidtoorder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // Maps to the root of the application
    public String home() {
        return "home"; // Maps to home.html in the "templates" folder
    }
}