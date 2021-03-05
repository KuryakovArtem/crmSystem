package com.crm.crm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentsController {
    @GetMapping("/Students")
    public String studentsMain(Model model){
        return "students-main";
    }
}

