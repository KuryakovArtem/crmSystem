package com.crm.crm.controllers;

import com.crm.crm.models.Students;
import com.crm.crm.repos.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentsController {

    @Autowired
    private StudentsRepository studentsRepository;


    @GetMapping("/Students")
    public String studentsMain(Model model){
        Iterable<Students> students = studentsRepository.findAll();
        model.addAttribute("students",students);
        return "students-main";
    }

    @GetMapping("/Students/add")
    public String studentsAdd(Model model){
        return "students-add";
    }
}

