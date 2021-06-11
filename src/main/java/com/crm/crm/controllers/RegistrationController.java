package com.crm.crm.controllers;

import com.crm.crm.entity.User;
import com.crm.crm.entity.UserRole;
import com.crm.crm.models.Instructors;
import com.crm.crm.models.Students;
import com.crm.crm.repos.InstructorsRepository;
import com.crm.crm.repos.StudentsRepository;
import com.crm.crm.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentsRepository studentsRepository;
    @Autowired
    InstructorsRepository instructorsRepository;

    @GetMapping("/registration")
    public String registration(Model model){
        User user = new User();
        model.addAttribute("user",user);
        List<String> listRole = Arrays.asList("STUDENT","TEACHER","ADMIN");
        model.addAttribute("listRole",listRole); // в html <select>
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model, @RequestParam String userRole){
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null)
        {
            model.put("message","User exists");
            return "registration";
        }
        if (userRole.equals("STUDENT")) {
            Students students = new Students(user.getUserFirstName(), user.getUserSecondName(), user.getUserPatronymic(), 1000, "");
            studentsRepository.save(students);
            user.setRoles(Collections.singleton(UserRole.STUDENT));
        }

        if(userRole.equals("TEACHER"))
        {
            Instructors instructors = new Instructors();
            instructors.setFirst_name(user.getUserFirstName());
            instructors.setSecond_name(user.getUserSecondName());
            instructors.setPatronymic(user.getUserPatronymic());
            instructors.setType_of_licence("A");
            instructors.setGroup("");

            instructorsRepository.save(instructors);
            user.setRoles(Collections.singleton(UserRole.TEACHER));
        }


        user.setActive(true);

        //user.setRoles(Collections.singleton(UserRole.STUDENT)); //This is a role placeholder
        userRepository.save(user);

        return "redirect:/login";
    }
}
