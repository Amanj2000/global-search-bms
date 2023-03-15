package com.globalsearch.service;

import com.globalsearch.dto.MovieRequestDTO;
import com.globalsearch.dto.MovieResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaConsumerService {
	@Autowired
	private MovieService movieService;

	@Autowired
	private SearchService searchService;

	@Autowired
	private KafkaTemplate<String, List<MovieResponseDTO>> searchResultProducer;

	@KafkaListener(topics = "movies", groupId = "movie-consumer-group", containerFactory = "movieListenerContainerFactory")
	public void listenMovie(MovieRequestDTO movieRequestDTO) {
		movieService.addMovie(movieRequestDTO);
	}

	@KafkaListener(topics = "search_query", groupId = "search-consumer-group", containerFactory = "searchListenerContainerFactory")
	public void listenSearchQuery(String query) {
		List<MovieResponseDTO> searchResult = searchService.searchMovie(query);
		searchResultProducer.send("search_result", query, searchResult);
	}
}
