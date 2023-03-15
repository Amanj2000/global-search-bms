package com.globalsearch.dto.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globalsearch.dto.MovieResponseDTO;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.util.List;

public class MovieDTOSerializer implements Serializer<List<MovieResponseDTO>> {
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public byte[] serialize(String s, List<MovieResponseDTO> movieResponseDTOS) {
		try {
			if(movieResponseDTOS == null) return null;
			return objectMapper.writeValueAsBytes(movieResponseDTOS);
		} catch (JsonProcessingException e) {
			throw new SerializationException("Error while serializing Movie " + e);
		}
	}
}
