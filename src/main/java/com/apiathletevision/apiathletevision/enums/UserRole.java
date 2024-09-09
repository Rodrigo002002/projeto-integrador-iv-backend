package com.apiathletevision.apiathletevision.enums;

public enum UserRole {
    ALUNO("aluno"),
    PROFESSOR("professor"),
    RESPONSAVEL("responsavel"),
    GESTOR("gestor");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
