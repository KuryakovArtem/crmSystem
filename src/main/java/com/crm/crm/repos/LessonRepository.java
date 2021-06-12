package com.crm.crm.repos;

import com.crm.crm.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
//    List<Lesson> findByUser(User user);
//    Lesson findByGroupname(String groupname);
    List<Lesson> findByMyGroup(java.lang.String myGroup);
}
