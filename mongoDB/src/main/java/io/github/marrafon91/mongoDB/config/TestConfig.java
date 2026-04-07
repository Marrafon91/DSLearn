package io.github.marrafon91.mongoDB.config;

import io.github.marrafon91.mongoDB.entities.Post;
import io.github.marrafon91.mongoDB.entities.User;
import io.github.marrafon91.mongoDB.entities.embedded.Author;
import io.github.marrafon91.mongoDB.entities.embedded.Comment;
import io.github.marrafon91.mongoDB.repositories.PostRepository;
import io.github.marrafon91.mongoDB.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.List;

@Configuration
@Profile("test")
public class TestConfig {

    @Bean
    CommandLineRunner init(
            UserRepository userRepository,
            PostRepository postRepository) {
        return args -> {

            userRepository.deleteAll();
            postRepository.deleteAll();

            User maria = new User(null, "Maria Brown", "maria@gmail.com");
            User alex = new User(null, "Alex Green", "alex@gmail.com");
            User bob = new User(null, "Bob Grey", "bob@gmail.com");

            userRepository.saveAll(List.of(maria, alex, bob));

            Post post1 = new Post(null, Instant.parse("2021-02-13T11:15:01Z"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new Author(maria));
            Post post2 = new Post(null, Instant.parse("2021-02-14T10:05:49Z"), "Bom dia", "Acordei feliz hoje!", new Author(maria));

            Comment c1 = new Comment("Boa viagem mano!", Instant.parse("2021-02-13T14:30:01Z"), new Author(alex));
            Comment c2 = new Comment("Aproveite", Instant.parse("2021-02-13T15:38:05Z"), new Author(bob));
            Comment c3 = new Comment("Tenha um ótimo dia!", Instant.parse("2021-02-14T12:34:26Z"), new Author(alex));

            post1.getComments().addAll(List.of(c1, c2));
            post2.getComments().add(c3);

            postRepository.saveAll(List.of(post1, post2));

            maria.getPosts().addAll(List.of(post1, post2));
            userRepository.save(maria);

            System.out.println("Seed executado com sucesso!");
            System.out.println("Total users: " + userRepository.count());
            System.out.println("Total posts: " + postRepository.count());
        };
    }
}
