package com.maidservice.maidtoorder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogIn {
    @GetMapping("/loginpage")
    public String login() {
        return "Maid_To_Order_Login";
    }
}
