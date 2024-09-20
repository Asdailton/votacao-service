package br.com.adam.studyingspringboot.dtos;

import br.com.adam.studyingspringboot.model.UserRole;

public record RegisterDTO  (String login, String password, UserRole role){
}
