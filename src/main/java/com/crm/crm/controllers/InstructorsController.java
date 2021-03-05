package com.crm.crm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InstructorsController {
    @GetMapping("/Instructors")
    public String instructorsMain(Model model){
        return "instructors-main";
    }
}

