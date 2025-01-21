package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("greeting", "Spring with Thymeleaf");
        return "hello";  // hello.html로 이동
    }
}
