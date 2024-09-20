package br.com.adam.studyingspringboot.dtos;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record PersonDTO(@NotBlank String name, String lastName, @NotNull  String country)  {}
