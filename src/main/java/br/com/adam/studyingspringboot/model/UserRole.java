package br.com.adam.studyingspringboot.model;

public enum UserRole {
    USER("user"), ADMIN("user");
    private  String role;
    UserRole(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
