package com.crm.crm.controllers;

import com.crm.crm.models.Groups;
import com.crm.crm.models.Students;
import com.crm.crm.repos.GroupsRepository;
import com.crm.crm.repos.StudentsRepository;
import com.crm.crm.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class StudentsController {
    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private GroupsRepository groupsRepository;

    @GetMapping("/students")
    public String studentsMain(Model model){
        Iterable<Students> students = studentsRepository.findAll();
        model.addAttribute("students",students); // "students" -> "usr" не работает
        return "students-main";
    }

    @GetMapping("/students/add")
    public String studentsAdd(){
        return "students-add";
    }

    @PostMapping("/students/add")
    public String newStudentsAdd(@RequestParam String first_name,
                                 @RequestParam String second_name,
                                 @RequestParam String patronymic,
                                 @RequestParam Integer balance,
                                 @RequestParam String group,
                                 Model model){

        Students students = new Students(first_name, second_name, patronymic, balance, group);
        studentsRepository.save(students);   //this is where students added to students table
        return  "redirect:/students";
    }


    @GetMapping("/students/{id}")
    public String studentsDetails(@PathVariable(value = "id") long id, Model model)    {
        if(!studentsRepository.existsById(id))        {
            return "redirect:/students";
        }
        Optional<Students> students = studentsRepository.findById(id);
        ArrayList<Students> res = new ArrayList<>();
        students.ifPresent(res::add);
        model.addAttribute("student", res);
        return "students-details";
    }

    @GetMapping("/students/{id}/edit")
    public String studentsEdit(@PathVariable(value = "id") long id, Model model)    {
        if(!studentsRepository.existsById(id))        {
            return "redirect:/students";
        }
        Iterable<Groups> groups = groupsRepository.findAll();
        model.addAttribute("groupsList", groups);
        Optional<Students> students = studentsRepository.findById(id);
        ArrayList<Students> res = new ArrayList<>();
        students.ifPresent(res::add);
        model.addAttribute("student", res);
        return "students-edit";
    }

    @PostMapping("/students/{id}/edit")
    public String studentPostUpgrade(@PathVariable(value = "id") long id,
                                     @RequestParam String first_name,
                                     @RequestParam String second_name,
                                     @RequestParam String patronymic,
                                     @RequestParam Integer balance,
                                     @RequestParam String my_group,
                                     Model model){
       Students student = studentsRepository.findById(id).orElseThrow();
       student.setFirst_name(first_name);
       student.setSecond_name(second_name);
       student.setPatronymic(patronymic);
       student.setBalance(balance);
       student.setMyGroup(my_group);
       studentsRepository.save(student);

       return "redirect:/students";
    }

    @PostMapping("/students/{id}/delete")
    public String studentPostRemove(@PathVariable(value = "id") long id,
                                     Model model){
        Students student = studentsRepository.findById(id).orElseThrow();
        studentsRepository.delete(student);

        return "redirect:/students";
    }
}

