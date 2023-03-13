package com.globalsearch.controller;

import com.globalsearch.dto.MovieRequestDTO;
import com.globalsearch.dto.MovieResponseDTO;
import com.globalsearch.dto.ResponseDTO;
import com.globalsearch.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

	@Autowired
	private MovieService movieService;

	@GetMapping
	public ResponseEntity<?> getAllMovies() {
		List<MovieResponseDTO> movieResponseDTOS = movieService.getAllMovies();
		return new ResponseEntity<>(movieResponseDTOS, HttpStatus.OK);
	}

	@GetMapping("/{movieId}")
	public ResponseEntity<?> getMovie(@PathVariable int movieId) {
		MovieResponseDTO movieResponseDTO = movieService.getMovie(movieId);
		return new ResponseEntity<>(movieResponseDTO, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> addMovie(@Valid @RequestBody MovieRequestDTO movieRequestDTO) {
		ResponseDTO responseDTO = movieService.addMovie(movieRequestDTO);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	@PutMapping("/{movieId}")
	public ResponseEntity<?> updateMovie(@PathVariable int movieId, @Valid @RequestBody MovieRequestDTO movieRequestDTO) {
		ResponseDTO responseDTO = movieService.updateMovie(movieId, movieRequestDTO);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	@DeleteMapping("/{movieId}")
	public ResponseEntity<?> deleteMovie(@PathVariable int movieId) {
		ResponseDTO responseDTO = movieService.deleteMovie(movieId);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}
}
