package com.globalsearch.config;

import com.globalsearch.dto.MovieRequestDTO;
import com.globalsearch.dto.deserializer.MovieDTODeserializer;
import com.globalsearch.service.MovieService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
	@Autowired
	private MovieService movieService;

	@Value(value = "${spring.kafka.bootstrap-servers}")
	private String bootstrapServer;

	@Bean
	public ConsumerFactory<Integer, MovieRequestDTO> movieConsumerFactory() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		configs.put(ConsumerConfig.CLIENT_ID_CONFIG, "movie-consumer-99");
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, "movie-consumer-group");
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, MovieDTODeserializer.class);
		return new DefaultKafkaConsumerFactory<>(configs);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<Integer, MovieRequestDTO> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<Integer, MovieRequestDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(movieConsumerFactory());
		return factory;
	}

	@KafkaListener(topics = "movies", groupId = "movie-consumer-group")
	public void listenMovie(MovieRequestDTO movieRequestDTO) {
		movieService.addMovie(movieRequestDTO);
	}
}
