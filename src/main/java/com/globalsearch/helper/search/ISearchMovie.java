package com.globalsearch.helper.search;

import com.globalsearch.model.Movie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ISearchMovie {
	List<Movie> search(String str);
}
