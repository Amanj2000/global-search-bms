package com.globalsearch.helper.search;

import com.globalsearch.model.Movie;
import com.globalsearch.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchByGenre implements ISearchMovie {
	@Autowired
	private MovieRepository movieRepository;

	@Override
	public List<Movie> search(String genre) {
		return movieRepository.findByGenreFuzzy(genre);
	}
}
