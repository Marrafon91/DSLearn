package io.github.marrafon91.mongoDB.entities.embedded;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "comments")
public class Comment {

    private String content;
    private Instant moment;

    private Author author;

    public Comment() {
    }

    public Comment(String content, Instant moment, Author author) {
        this.content = content;
        this.moment = moment;
        this.author = author;
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
}
