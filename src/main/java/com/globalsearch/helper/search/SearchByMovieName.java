package com.globalsearch.helper.search;

import com.globalsearch.model.Movie;
import com.globalsearch.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SearchByMovieName implements ISearchMovie {
	@Autowired
	private MovieRepository movieRepository;

	@Override
	public List<Movie> search(String partialMovieName) {
		Set<Movie> movies = new HashSet<>();
		movies.addAll(movieRepository.findByTitleContaining(partialMovieName));
		movies.addAll(movieRepository.findByTitleFuzzy(partialMovieName));
		return new ArrayList<>(movies);
	}
}
