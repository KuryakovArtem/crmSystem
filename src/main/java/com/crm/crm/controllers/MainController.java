package com.crm.crm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home screen");
        return "home";
    }

    @GetMapping("/reservation")
    public String reservation(Model model) {
        model.addAttribute("title","Reservation");
        return "reservation-main";
    }

    @GetMapping("/schedule")
    public String schedule(Model model) {
        model.addAttribute("title","Schedule");
        return "schedule-main";
    }

    @GetMapping("/instructors")
    public String instructors(Model model) {
        model.addAttribute("title","Instructors");
        return "instructors-main";
    }

    @GetMapping("/students")
    public String students(Model model) {
        model.addAttribute("title","Students");
        return "students-main";
    }

}
