package io.github.marrafon91.mongoDB.entities;

import io.github.marrafon91.mongoDB.entities.embedded.Author;
import io.github.marrafon91.mongoDB.entities.embedded.Comment;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "posts")
public class Post {
    @Id
    private String id;
    private Instant moment;
    
    @Indexed(unique = true)
    private String title;
    private String content;

    private Author author;

    private List<Comment> comments = new ArrayList<>();

    public Post() {
    }

    public Post(String id, Instant moment, String title, String content, Author author) {
        this.id = id;
        this.moment = moment;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Post post)) return false;

        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
