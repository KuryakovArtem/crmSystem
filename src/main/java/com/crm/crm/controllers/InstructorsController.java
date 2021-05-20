package com.crm.crm.controllers;

import com.crm.crm.models.Instructors;
import com.crm.crm.models.Students;
import com.crm.crm.repos.InstructorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

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

    @GetMapping("instructors/add")
    public String instructorsAdd(Model model){
        return "instructors-add";
    }

    @PostMapping("instructors/add")
    public String newInstructorsAdd(@RequestParam String first_name,
                                    @RequestParam String second_name,
                                    @RequestParam String patronymic,
                                    @RequestParam String type_of_licence,
                                    Model model){
        Instructors instructors = new Instructors(first_name, second_name, patronymic, type_of_licence);
        instructorsRepository.save(instructors);
        return "redirect:/instructors";
    }

    @GetMapping("/instructors/{id}")
    public String instructorsDetails(@PathVariable(value = "id") long id, Model model)    {
        if(!instructorsRepository.existsById(id))        {
            return "redirect:/instructors";
        }
        Optional<Instructors> instructors = instructorsRepository.findById(id);
        ArrayList<Instructors> res = new ArrayList<>();
        instructors.ifPresent(res::add);
        model.addAttribute("instructor", res);
        return "instructors-details";
    }

    @GetMapping("/instructors/{id}/edit")
    public String instructorsEdit(@PathVariable(value = "id") long id, Model model)    {
        if(!instructorsRepository.existsById(id))        {
            return "redirect:/students";
        }
        Optional<Instructors> instructors = instructorsRepository.findById(id);
        ArrayList<Instructors> res = new ArrayList<>();
        instructors.ifPresent(res::add);
        model.addAttribute("instructors", res);
        return "instructors-edit";
    }

    @PostMapping("/instructors/{id}/edit")
    public String studentPostUpgrade(@PathVariable(value = "id") long id,
                                     @RequestParam String first_name,
                                     @RequestParam String second_name,
                                     @RequestParam String patronymic,
                                     @RequestParam String type_of_license,
                                     Model model){
        Instructors instructor = instructorsRepository.findById(id).orElseThrow();
        instructor.setFirst_name(first_name);
        instructor.setSecond_name(second_name);
        instructor.setPatronymic(patronymic);
        instructor.setType_of_licence(type_of_license);
        instructorsRepository.save(instructor);

        return "redirect:/instructors";
    }

    @PostMapping("/instructors/{id}/delete")
    public String instructorPostRemove(@PathVariable(value = "id") long id,
                                    Model model)
    {
        Instructors instructor = instructorsRepository.findById(id).orElseThrow();
        instructorsRepository.delete(instructor);

        return "redirect:/instructors";
    }

}

