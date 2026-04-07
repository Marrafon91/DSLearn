package io.github.marrafon91.mongoDB.entities;

import jakarta.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Document(collection = "comments")
public class Comment {

    @Id
    private UUID id;
    private Instant date;

    public Comment() {
    }

    public Comment(UUID id, Instant date) {
        this.id = id;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Comment comment)) return false;

        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
