package io.github.marrafon91.mongoDB.repositories;

import io.github.marrafon91.mongoDB.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
