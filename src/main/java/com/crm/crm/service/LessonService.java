package com.crm.crm.service;

import com.crm.crm.entity.Lesson;
import com.crm.crm.repos.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

//    public void addLesson(Lesson lesson, User user){
//        lesson.setUser(user);
//        lessonRepository.save(lesson);
//    }
    public void addLesson(Lesson lesson, java.lang.String groups){
        lesson.setMyGroup(groups);
        lessonRepository.save(lesson);
    }

    public List<Lesson> findByMyGroup(java.lang.String myGroup){
        return lessonRepository.findByMyGroup(myGroup);
    }
//    public List<Lesson> findUserLesson(User user){
//        return lessonRepository.findByUser(user);
//    }
}
