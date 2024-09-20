package br.com.adam.studyingspringboot.model;

public enum ComentarioRole {
  COLABORADOR("role"), VISITANTE("role");
  private String role;

    ComentarioRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
