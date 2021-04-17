package com.crm.crm.controllers;

import com.crm.crm.models.Instructors;
import com.crm.crm.repos.InstructorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InstructorsController {

    @Autowired
    private InstructorsRepository instructorsRepository;

    @GetMapping("/instructors")
    public String instructorsMain(Model model){
        Iterable<Instructors> instructors = instructorsRepository.findAll();
        model.addAttribute("instructors", instructors);
        return "instructors-main";
    }
}

