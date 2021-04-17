package com.crm.crm.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScheduleController {
    @GetMapping("/schedule")
    public String scheduleMain(Model model)    {
        return "schedule-main";
    }
}

