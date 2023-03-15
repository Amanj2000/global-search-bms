package com.globalsearch.config;

import com.globalsearch.dto.MovieRequestDTO;
import com.globalsearch.dto.deserializer.MovieDTODeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
	@Value(value = "${spring.kafka.bootstrap-servers}")
	private String bootstrapServer;

	@Bean
	public ConsumerFactory<Integer, MovieRequestDTO> movieConsumerFactory() {
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		config.put(ConsumerConfig.CLIENT_ID_CONFIG, "movie-consumer");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "movie-consumer-group");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, MovieDTODeserializer.class);
		return new DefaultKafkaConsumerFactory<>(config);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<Integer, MovieRequestDTO> movieListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<Integer, MovieRequestDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(movieConsumerFactory());
		return factory;
	}

	@Bean
	public ConsumerFactory<String, String> searchConsumerFactory() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		configs.put(ConsumerConfig.CLIENT_ID_CONFIG, "search-consumer");
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, "search-consumer-group");
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(configs);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> searchListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(searchConsumerFactory());
		return factory;
	}
}
