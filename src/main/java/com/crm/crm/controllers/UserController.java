package com.crm.crm.controllers;

import com.crm.crm.entity.User;
import com.crm.crm.entity.UserRole;
import com.crm.crm.models.Instructors;
import com.crm.crm.models.Students;
import com.crm.crm.repos.InstructorsRepository;
import com.crm.crm.repos.StudentsRepository;
import com.crm.crm.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('STUDENT')") //ПОМЕНЯТЬ НА АДМИНА
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentsRepository studentsRepository;
    @Autowired
    private InstructorsRepository instructorsRepository;

    @GetMapping
    public String userList(Model model){
        model.addAttribute("users",userRepository.findAll());
        return "user-list";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user",user);
        model.addAttribute("role", UserRole.values());
        return "user-edit";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam ("id")User user){
        user.setUsername(username);
        Set<String> roles = Arrays.stream(UserRole.values())
                .map(UserRole::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();

        for (String key : form.keySet())
        {
            if(roles.contains(key)){
                user.getRoles().add(UserRole.valueOf(key));
            }
        }

        userRepository.save(user);
        return "redirect:/user";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public @ResponseBody
    String userRemove(@PathVariable(value = "id") long id,
                             Model model){
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);

        try {
            Students student = studentsRepository.findById(id-1).orElseThrow();
            studentsRepository.delete(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Instructors instructor = instructorsRepository.findById(id-1).orElseThrow();
            instructorsRepository.delete(instructor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/user";
    }

}
