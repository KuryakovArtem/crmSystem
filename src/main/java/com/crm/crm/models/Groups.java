package com.crm.crm.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String group_name;
    private String teacher_id;



//    @OneToMany(mappedBy = "group_name", targetEntity = Groups.class)
//    private List<Students> students;
//    @OneToOne(targetEntity = Groups.class) //mappedBy = "group_name",
//    private Instructors instructor;

//    public List<Students> getStudents() {
//        return students;
//    }

//    public Instructors getInstructor() {
//        return instructor;
//    }
//
//    public void setInstructor(Instructors instructor) {
//        this.instructor = instructor;
//    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }




//    public void setStudents(List<Students> students) {
//        if (students != null)
//            students.forEach(g ->
//                    g.setGroups(this)
//            );
////        this.students = students;
//    }
    public Groups(){}

    public Groups(Long id, String group_name, String teacher_id) {
        this.id = id;
        this.group_name = group_name;
        this.teacher_id = teacher_id;
    }
}
