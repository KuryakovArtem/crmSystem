package com.crm.crm.controllers;

import com.crm.crm.entity.User;
import com.crm.crm.entity.UserRole;
import com.crm.crm.models.Instructors;
import com.crm.crm.models.Students;
import com.crm.crm.repos.InstructorsRepository;
import com.crm.crm.repos.StudentsRepository;
import com.crm.crm.repos.UserRepository;
import com.crm.crm.service.MailSender;
import com.crm.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentsRepository studentsRepository;
    @Autowired
    InstructorsRepository instructorsRepository;
    @Autowired
    UserService userService;


    @Autowired
    MailSender mailSender;

    @GetMapping("/registration")
    public String registration(Model model){
        User user = new User();
        model.addAttribute("user",user);
        List<String> listRole = Arrays.asList("STUDENT","TEACHER","ADMIN");
        model.addAttribute("listRole",listRole); // в html <select>
        return "registration";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivated = userService.activateUser(code);

        if(isActivated){
            model.addAttribute("message","User activated");
        }
        else{
            model.addAttribute("message", "activationCodeIsNotFound");
        }

        return "login";
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
        }else



        user.setActive(true);

        //user.setRoles(Collections.singleton(UserRole.STUDENT)); //This is a role placeholder
        user.setActivationCode(UUID.randomUUID().toString());
        userRepository.save(user);
        if(!(user.getEmail().isEmpty())){
            String message = String.format(
                    "Hello, %s!\n" +
                            " Welcome to Spring CRM Autoschool!" +
                            " please, visit next link: http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            mailSender.send(user.getEmail(), "Activation code", message);
        }
        return "redirect:/login";
    }
}
