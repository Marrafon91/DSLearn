package io.github.marrafon91.mongoDB.repositories;

import io.github.marrafon91.mongoDB.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UUID> {
}
