package br.com.adam.studyingspringboot.dtos;

import br.com.adam.studyingspringboot.model.ComentarioRole;
import br.com.adam.studyingspringboot.model.Opcao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ComentDTO(
        @NotNull @NotBlank String nome,
        @NotNull Opcao opcao,
        @NotNull @NotBlank String comentario,
        @NotNull @NotBlank String timestampp,
        @NotNull Integer estrela
) {
}
