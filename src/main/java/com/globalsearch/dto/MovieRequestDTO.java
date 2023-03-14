package com.globalsearch.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequestDTO {
	@NotNull
	private Integer id;

	@NotBlank
	private String title;

	@NotNull
	private String description;

	@NotNull @Positive
	@Digits(integer = 3, fraction = 0)
	private int duration;

	@NotBlank
	private String language;

	@NotBlank
	private String genre;

	@NotEmpty
	private List<String> cast;

	@Override
	public String toString() {
		return String.format("id: %d \t title: %s \t desc: %s \t duration: %d \t language: %s \t genre: %s \t cast: %s\n",
				id, title, description, duration, language, genre, cast);
	}
}
