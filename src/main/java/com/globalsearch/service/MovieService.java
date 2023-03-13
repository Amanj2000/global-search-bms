package com.globalsearch.service;

import com.globalsearch.dto.MovieRequestDTO;
import com.globalsearch.dto.MovieResponseDTO;
import com.globalsearch.dto.ResponseDTO;
import com.globalsearch.helper.MovieHelper;
import com.globalsearch.model.Movie;
import com.globalsearch.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {
	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private MovieHelper movieHelper;

	public List<MovieResponseDTO> getAllMovies() {
		List<MovieResponseDTO> movies = new ArrayList<>();
		movieRepository.findAll()
		               .forEach(movie -> movies.add(new MovieResponseDTO(movie)));
		return movies;
	}

	public MovieResponseDTO getMovie(int movieId) {
		Movie movie = movieHelper.getMovie(movieId);
		return new MovieResponseDTO(movie);
	}

	public ResponseDTO addMovie(MovieRequestDTO movieRequestDTO) {
		movieHelper.canAdd(movieRequestDTO.getId(), movieRequestDTO.getGenre());

		Movie movie = new Movie();
		movieHelper.mapMovieRequestToMovie(movieRequestDTO, movie);
		movieRepository.save(movie);
		return new ResponseDTO(String.format("movie %s added successfully", movie.getTitle()));
	}

	public ResponseDTO updateMovie(int movieId, MovieRequestDTO movieRequestDTO) {
		movieHelper.canUpdate(movieId, movieRequestDTO.getId(), movieRequestDTO.getGenre());

		Movie movie = movieHelper.getMovie(movieId);
		movieHelper.mapMovieRequestToMovie(movieRequestDTO, movie);
		movieRepository.save(movie);
		return new ResponseDTO(String.format("movie %s updated successfully", movie.getTitle()));
	}

	public ResponseDTO deleteMovie(int movieId) {
		Movie movie = movieHelper.getMovie(movieId);
		movieRepository.delete(movie);
		return new ResponseDTO(String.format("movie %s deleted successfully", movie.getTitle()));
	}
}
