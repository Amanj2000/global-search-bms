package com.globalsearch.config;

import com.globalsearch.dto.MovieResponseDTO;
import com.globalsearch.dto.serializer.MovieDTOSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
	@Value(value = "${spring.kafka.bootstrap-servers}")
	private String bootstrapServer;

	@Bean
	public ProducerFactory<String, List<MovieResponseDTO>> searchResultProducerFactory() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		configs.put(ProducerConfig.CLIENT_ID_CONFIG, "search-result-producer");
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, MovieDTOSerializer.class);
		return new DefaultKafkaProducerFactory<>(configs);
	}

	@Bean
	public KafkaTemplate<String, List<MovieResponseDTO>> searchResultKafkaTemplate() {
		return new KafkaTemplate<>(searchResultProducerFactory());
	}
}
