package io.github.marrafon91.mongoDB.entities;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Objects;

@Document(collection = "comments")
public class Comment {

    @Id
    private String id;
    private Instant date;

    public Comment() {
    }

    public Comment(String id, Instant date) {
        this.id = id;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
