package com.devsuperior.dsmovie.services;

import com.devsuperior.dsmovie.dto.MovieGenreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.entities.Movie;
import com.devsuperior.dsmovie.repositories.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;
	
	@Transactional(readOnly = true)
	public Page<MovieDTO> findAll(Pageable pageable) {
		Page<Movie> result = repository.findAll(pageable);
        return result.map(MovieDTO::new);
	}

	@Transactional(readOnly = true)
	public Page<MovieGenreDTO> findAllWithGenre(Pageable pageable) {
		Page<Movie> result = repository.findAll(pageable);
        return result.map(MovieGenreDTO::new);
	}
	
	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		Movie result = repository.findById(id).get();
        return new MovieDTO(result);
	}

	@Transactional(readOnly = true)
	public MovieGenreDTO findByIdMovieGenre(Long id) {
		Movie result = repository.findById(id).get();
        return new MovieGenreDTO(result);
	}
}
