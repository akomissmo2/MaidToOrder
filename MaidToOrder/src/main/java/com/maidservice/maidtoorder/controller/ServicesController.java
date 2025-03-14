package com.maidservice.maidtoorder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServicesController {

    @GetMapping("/services")
    public String services() {
        return "services"; // Render a services page
    }
}
