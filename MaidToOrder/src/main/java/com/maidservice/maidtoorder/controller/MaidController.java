package com.maidservice.maidtoorder.controller;

import com.maidservice.maidtoorder.service.MaidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MaidController {

    @Autowired
    private MaidService maidService;

    // Handle GET request to display the maids page
    @GetMapping("/maids")
    public String viewMaidPage(Model model) {
        // Add list of maids to the model
        model.addAttribute("maids", maidService.getAllMaids());
        return "maids"; // Refers to the Thymeleaf HTML page `maids.html`
    }
}