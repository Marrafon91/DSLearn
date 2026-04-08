package io.github.marrafon91.mongoDB.services;

import io.github.marrafon91.mongoDB.dtos.PostDTO;
import io.github.marrafon91.mongoDB.dtos.UserDTO;
import io.github.marrafon91.mongoDB.entities.Post;
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

    public UserDTO insert(UserDTO dto) {
        User entity = new User();
        copyDtoToEntity(dto, entity);

        entity = userRepository.insert(entity);

        return new UserDTO(entity);
    }

    public UserDTO update(String id, UserDTO dto) {

        User entity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado. ID: " + id));

        copyDtoToEntity(dto, entity);
        entity = userRepository.save(entity);

        return new UserDTO(entity);
    }

    public void delete(String id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado. ID: " + id);
        }
        userRepository.deleteById(id);
    }

    public List<PostDTO> getUserPosts(String id) {
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado. ID: " + id));
        return entity.getPosts().stream().map(PostDTO::new).toList();
    }

    private void copyDtoToEntity(UserDTO dto, User entity) {
        entity.setName(dto.name());
        entity.setEmail(dto.email());
    }
}
