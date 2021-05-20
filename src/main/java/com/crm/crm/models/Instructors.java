package com.crm.crm.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Instructors {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String first_name;
    private String second_name;
    private String patronymic;
    private String type_of_licence;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getType_of_licence() {
        return type_of_licence;
    }

    public void setType_of_licence(String type_of_licence) {
        this.type_of_licence = type_of_licence;
    }

    public Instructors(){}

    public Instructors(String first_name, String second_name, String patronymic, String type_of_licence){
        this.first_name = first_name;
        this.second_name = second_name;
        this.patronymic = patronymic;
        this.type_of_licence = type_of_licence;
    }
}
