package com.crm.crm.entity;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "usr")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private java.lang.String username;
    private java.lang.String userFirstName;
    private java.lang.String userSecondName;
    private java.lang.String userPatronymic;
    private java.lang.String email;
    private java.lang.String password;
    private java.lang.String userRole;
    //private String lessons;



    private String activationCode;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.lang.String getUsername() {
        return username;
    }

    public void setUsername(java.lang.String username) {
        this.username = username;
    }

    public java.lang.String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(java.lang.String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public java.lang.String getUserSecondName() {
        return userSecondName;
    }

    public void setUserSecondName(java.lang.String userSecondName) {
        this.userSecondName = userSecondName;
    }

    public java.lang.String getUserPatronymic() {
        return userPatronymic;
    }

    public void setUserPatronymic(java.lang.String userPatronymic) {
        this.userPatronymic = userPatronymic;
    }

    public java.lang.String getEmail() {
        return email;
    }

    public void setEmail(java.lang.String email) {
        this.email = email;
    }

    public java.lang.String getPassword() {
        return password;
    }

    public void setPassword(java.lang.String password) {
        this.password = password;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public Boolean getLocked() {
        return isLocked;
    }

    public void setActive(Boolean locked) {
        isLocked = locked;
    }

    private Boolean isLocked;

    public java.lang.String getUserRole() {
        return userRole;
    }

    public void setUserRole(java.lang.String userRole) {
        this.userRole = userRole;
    }

//    public String getLessons() {
//        return lessons;
//    }
//
//    public void setLessons(String lessons) {
//        this.lessons = lessons;
//    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

}


