package com.crm.crm.controllers;

import com.crm.crm.models.Students;
import com.crm.crm.repos.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/Students/add")
    public String newStudentsAdd(@RequestParam String first_name,
                                 @RequestParam String second_name,
                                 @RequestParam String patronymic,
                                 @RequestParam Integer balance,
                                 Model model){

        Students students = new Students(first_name, second_name, patronymic, balance);
        studentsRepository.save(students);
        return  "redirect:/Students";
    }

}

