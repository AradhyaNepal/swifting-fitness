package com.a2.swifting_fitness.common.enums;

public enum UserRole {
    user,
    admin;
    public String toRoleString(){
        return switch (this) {
            case user -> "user";
            case admin -> "admin";
        };
    }
}
