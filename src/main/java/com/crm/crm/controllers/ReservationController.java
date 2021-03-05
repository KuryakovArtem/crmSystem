package com.crm.crm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationController {
    @GetMapping("/Reservation")
    public String reservationMain(Model model)    {
        return "reservation-main";
    }
}
