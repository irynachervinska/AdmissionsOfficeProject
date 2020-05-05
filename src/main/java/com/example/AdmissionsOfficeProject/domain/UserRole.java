package com.example.AdmissionsOfficeProject.domain;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_ENROLLEE;

    @Override
    public String getAuthority() {
        return name();
    }
}
