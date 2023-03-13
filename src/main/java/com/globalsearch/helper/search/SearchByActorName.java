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
public class SearchByActorName implements ISearchMovie {
	@Autowired
	private MovieRepository movieRepository;

	@Override
	public List<Movie> search(String partialActorName) {
		Set<Movie> movies = new HashSet<>();
		movies.addAll(movieRepository.findByActorsNameContaining(partialActorName));
		movies.addAll(movieRepository.findByActorsNameFuzzy(partialActorName));
		return new ArrayList<>(movies);
	}
}
