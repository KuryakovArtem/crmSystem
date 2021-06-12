package com.crm.crm.controllers;

import javax.validation.Valid;

import com.crm.crm.entity.Lesson;
import com.crm.crm.models.Groups;
import com.crm.crm.repos.GroupsRepository;
import com.crm.crm.repos.LessonRepository;
import com.crm.crm.service.GroupService;
import com.crm.crm.service.LessonService;
import com.crm.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LessonController {
    @Autowired
    private LessonService lessonServicee;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private GroupsRepository groupsRepository;
    @Autowired
    private LessonRepository lessonRepository;

    @GetMapping("/lesson")
    public java.lang.String lessonMain(Model model){//TODO еще пробросить группу через айдишник чтобы выводить название


        Iterable<Lesson> lessons = lessonRepository.findAll();
        model.addAttribute("lesson",lessons);

//        List<Long> groupsIdFromLessonTable = new ArrayList<>();
//        for (Lesson elem : lessons)
//        {
//            groupsIdFromLessonTable.add(Long.parseLong(elem.getMyGroup()));
//        }
//        Iterable<Groups> groups = groupsRepository.findAllById(groupsIdFromLessonTable);
        Iterable<Groups> groups = groupsRepository.findAll();//TODO тупо пробрасываются все группы а надо как то заматчить группы и уроки
        model.addAttribute("groups",groups);

        return "lesson-main";
    }

    @GetMapping("/lesson/add")
    public java.lang.String addLessonForm(Model model){
        Iterable<Groups> groups = groupsRepository.findAll();
        model.addAttribute("groups", groups);
        model.addAttribute("lesson", new Lesson());
        return "lesson-add";
    }

    @PostMapping("/lesson/add")
    public java.lang.String addLesson(@Valid Lesson lesson,
                                      @RequestParam java.lang.String my_group){
        lesson.setMyGroup(my_group);
        lessonServicee.addLesson(lesson, my_group);
        return "redirect:/lesson";
    }



}