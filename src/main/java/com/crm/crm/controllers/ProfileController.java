package com.crm.crm.controllers;

import com.crm.crm.entity.Lesson;
import com.crm.crm.entity.User;
import com.crm.crm.models.Students;
import com.crm.crm.repos.GroupsRepository;
import com.crm.crm.repos.StudentsRepository;
import com.crm.crm.service.LessonService;
import com.crm.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {
    @Autowired
    private LessonService lessonService;
    @Autowired
    private UserService userService;
    @Autowired
    private StudentsRepository studentsRepository;
    @Autowired
    private GroupsRepository groupsRepository;

    @GetMapping("/profile")
    public String showProfilePage(Model model, Principal principal){
        String un = principal.getName();
        User user = userService.findByUsername(un);
        Optional<Students> myStudent = studentsRepository.findById(user.getId()-1);
        Long groupId = Long.parseLong(myStudent.get().getMyGroup());
        model.addAttribute("user", user);
        model.addAttribute("student", myStudent);
        model.addAttribute("groups",groupsRepository.findById(groupId));//TODO лучше пробросить не группу а занятия
//        String email = principal.getName();
//        User user = userService.findByEmail(email);
        List<Lesson> lessons = lessonService.findByMyGroup(myStudent.get().getMyGroup());
        model.addAttribute("lessons", lessons);

        return "profile-main";
    }
}
