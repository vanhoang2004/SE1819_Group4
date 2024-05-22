package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/test")
public class ExampleController {
@GetMapping("/homepage")
    public String list() {
    return "home";
}
    @GetMapping("/editpage")
public String list1() {
    return "edit";
}
    @GetMapping("/listpage")
    public String list2() {
        return "list";
    }
}
