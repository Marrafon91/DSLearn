package io.github.marrafon91.mongoDB.dtos;

import io.github.marrafon91.mongoDB.entities.Post;
import io.github.marrafon91.mongoDB.entities.embedded.Author;
import io.github.marrafon91.mongoDB.entities.embedded.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.List;

public record PostDTO(

        String id,
        Instant moment,
        @NotBlank(message = "Campo obrigatório")
        @Size(min = 3, max = 100, message = "Campo fora do tamanho padrão")
        String title,
        @NotBlank(message = "Campo obrigatório")
        String content,
        @NotNull(message = "Campo obrigatório")
        Author author,
        List<Comment> comments
) {

    public PostDTO(Post entity) {
        this(
                entity.getId(),
                entity.getMoment(),
                entity.getTitle(),
                entity.getContent(),
                entity.getAuthor(),
                entity.getComments()
        );
    }
}
