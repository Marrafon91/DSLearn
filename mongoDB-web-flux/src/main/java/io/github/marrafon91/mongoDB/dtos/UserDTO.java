package io.github.marrafon91.mongoDB.dtos;

import io.github.marrafon91.mongoDB.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDTO(

        String id,

        @NotBlank(message = "Campo obrigatório")
        @Size(min = 3, max = 100, message = "Campo fora do tamanho padrão")
        String name,

        @NotBlank(message = "Campo obrigatório")
        @Email(message = "E-mail inválido!")
        String email
) {

    public UserDTO(User entity) {
        this(
                entity.getId(),
                entity.getName(),
                entity.getEmail()
        );
    }
}
