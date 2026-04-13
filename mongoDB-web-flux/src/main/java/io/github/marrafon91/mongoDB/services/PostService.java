package io.github.marrafon91.mongoDB.services;

import io.github.marrafon91.mongoDB.dtos.PostDTO;
import io.github.marrafon91.mongoDB.entities.Post;
import io.github.marrafon91.mongoDB.exceptions.ResourceNotFoundException;
import io.github.marrafon91.mongoDB.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.format.DateTimeParseException;
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
        Optional<Post> result = postRepository.findById(id);
        return result.orElseThrow(() -> new ResourceNotFoundException("Post não encontrado. ID: " + id));
    }

    public List<PostDTO> findByTitle(String text) {
        List<Post> result = postRepository.searchTitle(text);
        return result.stream().map(PostDTO::new).toList();
    }

    public List<PostDTO> findByFullSearch(String text, String start, String end) {

        Instant startMoment = ConvertMoment(start, Instant.ofEpochMilli(0L));
        Instant endMoment = ConvertMoment(end, Instant.now());

        List<Post> result = postRepository.fullSearch(text, startMoment, endMoment);
        return result.stream().map(PostDTO::new).toList();
    }

    private Instant ConvertMoment(String originalString, Instant alternative) {
        try {
            return Instant.parse(originalString);
        }
        catch (DateTimeParseException e) {
            return alternative;
        }
    }
}
