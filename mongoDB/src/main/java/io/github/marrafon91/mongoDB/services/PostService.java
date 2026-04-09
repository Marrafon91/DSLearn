package io.github.marrafon91.mongoDB.services;

import io.github.marrafon91.mongoDB.dtos.PostDTO;
import io.github.marrafon91.mongoDB.entities.Post;
import io.github.marrafon91.mongoDB.exceptions.ResourceNotFoundException;
import io.github.marrafon91.mongoDB.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public PostDTO findById(String id) {
        Optional<Post> result = postRepository.findById(id);
        return result.map(PostDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("Post não encontrado. ID: " + id));
    }

    public Post getUserPosts(String id) {
       Optional<Post>  result = postRepository.findById(id);
        return result.orElseThrow(() -> new ResourceNotFoundException("Post não encontrado. ID: " + id));
    }

    public List<PostDTO> findByTitle(String text) {
        List<Post> result = postRepository.searchTitle(text);
        return result.stream().map(PostDTO::new).toList();
    }
}
