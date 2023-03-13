package com.globalsearch.repository;

import com.globalsearch.model.Movie;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends ElasticsearchRepository<Movie, Integer> {
	List<Movie> findByTitleContaining(String partialMovieName);

	@Query("{ \"match\": { \"title\": { \"query\": \"?0\", \"fuzziness\": 1 } } }")
	List<Movie> findByTitleFuzzy(String title);

	List<Movie> findByDescriptionContaining(String desc);

	@Query("{ \"match\": { \"description\": { \"query\": \"?0\", \"fuzziness\": 1 } } }")
	List<Movie> findByDescriptionFuzzy(String desc);

	List<Movie> findByActorsNameContaining(String partialActorName);
	@Query("{ \"match\": { \"actors.name\": { \"query\": \"?0\", \"fuzziness\": 1 } } }")
	List<Movie> findByActorsNameFuzzy(String partialActorName);

	@Query("{ \"match\": { \"language\": { \"query\": \"?0\", \"fuzziness\": 1 } } }")
	List<Movie> findByLanguageFuzzy(String language);

	@Query("{ \"match\": { \"genre\": { \"query\": \"?0\", \"fuzziness\": 1 } } }")
	List<Movie> findByGenreFuzzy(String genre);
}
