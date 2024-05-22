package com.example.demo.controller;

import com.example.demo.data.MockTestRepository;
import com.example.demo.entity.Mocktest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MockTestController {

    private MockTestRepository mtr;

    @Autowired
    public MockTestController(MockTestRepository mtr) {
        this.mtr = mtr;
    }
    @GetMapping("/mocktests")
    public String getMocktests(Model model){
        List<Mocktest> mocktests = mtr.findAll();
        model.addAttribute("mocktests", mocktests);
        return "mocktests";
    }
}
