package com.globalsearch.service;

import com.globalsearch.dto.MovieRequestDTO;
import com.globalsearch.dto.MovieResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
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

	@KafkaListener(topics = "movies", groupId = "search-movie-consumer-group", containerFactory =
			"movieListenerContainerFactory")
	public void listenMovie(@Header(KafkaHeaders.RECEIVED_KEY) String operation, MovieRequestDTO movieRequestDTO) {
		switch (operation) {
			case "add" -> movieService.addMovie(movieRequestDTO);
			case "update" -> movieService.updateMovie(movieRequestDTO.getId(), movieRequestDTO);
			case "delete" -> movieService.deleteMovie(movieRequestDTO.getId());
		}
	}

	@KafkaListener(topics = "search_query", groupId = "search-consumer-group", containerFactory = "searchListenerContainerFactory")
	public void listenSearchQuery(String query) {
		List<MovieResponseDTO> searchResult = searchService.searchMovie(query);
		searchResultProducer.send("search_result", query, searchResult);
	}
}
