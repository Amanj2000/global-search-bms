package com.globalsearch.service;

import com.globalsearch.dto.MovieRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
	@Autowired
	private MovieService movieService;

	@KafkaListener(topics = "movies", groupId = "search-movie-consumer-group", containerFactory = "movieListenerContainerFactory")
	public void listenMovie(@Header(KafkaHeaders.RECEIVED_KEY) String operation, MovieRequestDTO movieRequestDTO) {
		switch (operation) {
			case "add" -> movieService.addMovie(movieRequestDTO);
			case "update" -> movieService.updateMovie(movieRequestDTO.getId(), movieRequestDTO);
			case "delete" -> movieService.deleteMovie(movieRequestDTO.getId());
		}
	}
}
