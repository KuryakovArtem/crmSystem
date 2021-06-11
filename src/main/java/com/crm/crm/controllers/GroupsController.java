package com.crm.crm.controllers;

import com.crm.crm.entity.User;
import com.crm.crm.models.Groups;
import com.crm.crm.models.Instructors;
import com.crm.crm.models.Students;
import com.crm.crm.repos.GroupsRepository;
import com.crm.crm.repos.InstructorsRepository;
import com.crm.crm.repos.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class GroupsController {

    @Autowired
    GroupsRepository groupsRepository;

    @Autowired
    InstructorsRepository instructorsRepository;

    @Autowired
    StudentsRepository studentsRepository;

    @GetMapping("/group")
    public String reservationMain(Model model){
        Iterable<Groups> groups = groupsRepository.findAll();
        model.addAttribute("my_group",groups);
        return "group-main";
    }

    @GetMapping("/group/add")
    public String createGroups(Model model){
//        Instructors instructors = new Instructors();
//        String initials = instructors.getFirst_name() + ' ' + instructors.getSecond_name();
//        model.addAttribute("instructors", initials);
//        Students students = new Students();
//        model.addAttribute("students", students);
//        Iterable<Instructors> instructorsList = instructorsRepository.findAll();
//        model.addAttribute("instructorsList", instructorsList);

//        ArrayList<Instructors> instructorsArrayList = new ArrayList<>();
        Iterable<Instructors> instructorsArrayList = instructorsRepository.findAll();
        model.addAttribute("instructorsList", instructorsArrayList);
        return "group-add";
    }

    @PostMapping("/group/add")
    public String newGroupsAdd(@RequestParam String group_name,
                               @RequestParam String instructor){

        Groups groups = new Groups();
        groups.setGroup_name(group_name);
        Long teacherIdFromForm = Long.parseLong(instructor);
        Instructors instructorFromForm = instructorsRepository.findById(teacherIdFromForm).orElseThrow();
        groups.setTeacher_id(instructorFromForm.getId().toString());
        groupsRepository.save(groups);
        instructorFromForm.setGroup(groups.getId().toString());

//        List<Students> studentsList = new ArrayList<>();
//        for (int i = 0; i < checkbox.size(); i++)
//        {
//            Long studentIdFromForm = Long.parseLong(checkbox.get(i));
//            Students studentFromForm = studentsRepository.findById(studentIdFromForm).orElseThrow();
//            studentFromForm.setGroups(groups);
//            studentsList.add(studentFromForm);
//        }
//        groups.setStudents(studentsList);




//        groups.getStudents().clear();
//        for(String s: checkbox.keySet()){
//            if
//        }


        return "redirect:/group";
    }

    @GetMapping("/group/{id}")
    public String groupDetails(@PathVariable(value = "id") long id, Model model){
        if(!groupsRepository.existsById(id))
        {
            return "redirect:/group";
        }
        Iterable<Students> studentList = studentsRepository.findAll();
        List<Students> inGroupStudents = new ArrayList<>();
        for(Students elem : studentList)
        {
            if(elem.getMyGroup().equals(""))
                continue;
            else {
                if (Long.parseLong(elem.getMyGroup()) == id) {
                    inGroupStudents.add(elem);
                }
            }
        }
        model.addAttribute("inGroupStudents",inGroupStudents);
        Optional<Groups> group = groupsRepository.findById(id);
        ArrayList<Groups> result = new ArrayList<>();
        group.ifPresent(result::add);
        model.addAttribute("group", result);
        return "group-details";
    }

}
