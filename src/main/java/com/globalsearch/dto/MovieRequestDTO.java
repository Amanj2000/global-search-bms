package com.globalsearch.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
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
}
