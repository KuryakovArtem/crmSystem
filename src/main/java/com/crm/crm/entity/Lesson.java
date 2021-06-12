package com.crm.crm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Lesson {

    @Id
    @GeneratedValue
    private Long id;

    private String date;
    private String startTime;
    private String stopTime;
    private String description;
    private String myGroup;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMyGroup() {
        return myGroup;
    }

    public void setMyGroup(java.lang.String myGroup) {
        this.myGroup = myGroup;
    }

    public Lesson() {
    }

    public Lesson(Long id, java.lang.String date, java.lang.String startTime, java.lang.String stopTime, java.lang.String description, java.lang.String myGroup) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.description = description;
        this.myGroup = myGroup;
    }
}