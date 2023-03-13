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
public class SearchByDescription implements ISearchMovie {
	@Autowired
	private MovieRepository movieRepository;

	@Override
	public List<Movie> search(String desc) {
		Set<Movie> movies = new HashSet<>();
		movies.addAll(movieRepository.findByDescriptionContaining(desc));
		movies.addAll(movieRepository.findByDescriptionFuzzy(desc));
		return new ArrayList<>(movies);
	}
}
