package io.github.marrafon91.mongoDB.services;

import io.github.marrafon91.mongoDB.dtos.UserDTO;
import io.github.marrafon91.mongoDB.entities.User;
import io.github.marrafon91.mongoDB.exceptions.ResourceNotFoundException;
import io.github.marrafon91.mongoDB.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public UserDTO findById(String id) {
        Optional<User> result = userRepository.findById(id);
        return result.map(UserDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado. ID: " + id));
    }
}
