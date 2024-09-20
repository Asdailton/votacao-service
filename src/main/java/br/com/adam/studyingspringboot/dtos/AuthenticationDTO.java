package br.com.adam.studyingspringboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthenticationDTO(@NotBlank String login, @NotNull String password) {
}
