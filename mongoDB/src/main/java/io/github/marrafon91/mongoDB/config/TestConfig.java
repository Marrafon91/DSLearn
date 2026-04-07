package io.github.marrafon91.mongoDB.config;

import io.github.marrafon91.mongoDB.entities.User;
import io.github.marrafon91.mongoDB.repositories.PostRepository;
import io.github.marrafon91.mongoDB.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

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

            System.out.println("Seed executado com sucesso!");
            System.out.println("Total users: " + userRepository.count());
        };
    }
}
