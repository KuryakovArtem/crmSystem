package com.crm.crm.models;

import javax.persistence.*;

@Entity
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String first_name;
    private String second_name;
    private String patronymic;
    private Integer balance;
    private String myGroup;



//    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "students", joinColumns = @JoinColumn(name = "id"))

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "students_id") // похоже создается поле в таблице группы
//    private Groups groups;

//    public Groups getGroups() {
//        return groups;
//    }
//
//    public void setGroups(Groups groups) {
//        this.groups = groups;
//    }

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

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getMyGroup() {
        return myGroup;
    }

    public void setMyGroup(String myGroup) {
        this.myGroup = myGroup;
    }
    public Students()
    {

    }

    public Students(String first_name, String second_name, String patronymic, Integer balance, String myGroup) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.patronymic = patronymic;
        this.balance = balance;
        this.myGroup = myGroup;
    }
}
