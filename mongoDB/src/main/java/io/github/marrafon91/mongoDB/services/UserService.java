package io.github.marrafon91.mongoDB.services;

import io.github.marrafon91.mongoDB.dtos.UserDTO;
import io.github.marrafon91.mongoDB.entities.User;
import io.github.marrafon91.mongoDB.exceptions.ResourceNotFoundException;
import io.github.marrafon91.mongoDB.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> findAll() {
        List<User> result = userRepository.findAll();

        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Usúario não encontrado! ");
        }
        return result.stream().map(UserDTO::new).toList();
    }
}
