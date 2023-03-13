package com.globalsearch.repository;

import com.globalsearch.model.Movie;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends ElasticsearchRepository<Movie, Integer> {
	List<Movie> findByTitleContaining(String partialMovieName);

	List<Movie> findByDescriptionContaining(String desc);

	List<Movie> findByActorsNameContaining(String partialActorName);

	List<Movie> findByLanguage(String language);

	List<Movie> findByGenre(String genre);
}
