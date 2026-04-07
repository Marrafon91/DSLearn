package io.github.marrafon91.mongoDB.entities.embedded;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Objects;

@Document(collection = "comments")
public class Comment {

    @Id
    private String id;
    private String content;
    private Instant moment;

    private Author author;

    public Comment() {
    }

    public Comment(String id,String content, Instant moment) {
        this.id = id;
        this.content = content;
        this.moment = moment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
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
