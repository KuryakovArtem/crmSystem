package com.crm.crm.entity;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    STUDENT,
    TEACHER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
