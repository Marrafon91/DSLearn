package io.github.marrafon91.mongoDB.controllers;

import io.github.marrafon91.mongoDB.dtos.PostDTO;
import io.github.marrafon91.mongoDB.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

    @Autowired
    private PostService service;

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<PostDTO>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        List<PostDTO> result = service.findByTitle(text);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/fullsearch")
    public ResponseEntity<List<PostDTO>> findByFullSearch(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "start", defaultValue = "") String start,
            @RequestParam(value = "end", defaultValue = "") String end) {

        List<PostDTO> result = service.findByFullSearch(text, start, end);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDTO> findById(@PathVariable String id) {
        PostDTO result = service.findById(id);
        return ResponseEntity.ok().body(result);
    }
}
