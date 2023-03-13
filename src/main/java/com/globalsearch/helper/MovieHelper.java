package com.globalsearch.helper;


import com.globalsearch.dto.MovieRequestDTO;
import com.globalsearch.exception.NotFoundException;
import com.globalsearch.model.Actor;
import com.globalsearch.model.Movie;
import com.globalsearch.model.enums.Genre;
import com.globalsearch.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class MovieHelper {
	@Autowired
	MovieRepository movieRepository;

	public void mapMovieRequestToMovie(MovieRequestDTO movieRequestDTO, Movie movie) {
		movie.setId(movieRequestDTO.getId());
		movie.setTitle(movieRequestDTO.getTitle());
		movie.setDescription(movieRequestDTO.getDescription());
		movie.setDuration(movieRequestDTO.getDuration());
		movie.setLanguage(movieRequestDTO.getLanguage());
		movie.setGenre(Genre.valueOf(movieRequestDTO.getGenre().toUpperCase()));
		movie.setActors(movieRequestDTO.getCast()
		                               .stream()
		                               .map(Actor::new)
		                               .collect(Collectors.toList()));
	}

	public Movie getMovie(int movieId) {
		Optional<Movie> movieOptional = movieRepository.findById(movieId);
		return movieOptional.orElseThrow(() -> new NotFoundException("invalid movie id"));
	}

	private void checkGenre(String genre) {
		try {
			Genre.valueOf(genre.toUpperCase());
		} catch(IllegalArgumentException e) {
			throw new IllegalArgumentException(String.format("invalid Genre type, select genre from %s",
					Arrays.toString(Genre.class.getEnumConstants())));
		}
	}

	public void canAdd(int movieId, String genre) {
		if(movieRepository.existsById(movieId))
			throw new IllegalArgumentException("movie with this id already exists");
		checkGenre(genre);
	}

	public void canUpdate(int movieId, int requestMovieId, String genre) {
		if(movieId != requestMovieId)
			throw new IllegalArgumentException("movie Ids does not match");
		checkGenre(genre);
	}
}
