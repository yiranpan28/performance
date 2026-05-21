package com.yiran.performance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("message", "Book Library System is running!");
        return "index";
    }
}