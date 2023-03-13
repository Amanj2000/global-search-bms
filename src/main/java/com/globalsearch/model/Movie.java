package com.globalsearch.model;

import com.globalsearch.model.enums.Genre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter @Setter
@NoArgsConstructor
@Document(indexName = "movies")
@Setting(settingPath = "/static/lowercase_normalizer.json")
public class Movie {
	@Id
	@Field(type = FieldType.Integer)
	private Integer id;

	@Field(type = FieldType.Text)
	private String title;

	@Field(type = FieldType.Text)
	private String description;

	@Field(type = FieldType.Integer, index = false)
	private Integer duration;

	@Field(type = FieldType.Keyword, normalizer = "lowercase_normalizer")
	private String language;

	@Field(type = FieldType.Keyword, normalizer = "lowercase_normalizer")
	private Genre genre;

	@Field(type = FieldType.Nested, includeInParent = true)
	private List<Actor> actors = new ArrayList<>();

	public Movie(int id, String title, String description, Integer duration, String language, Genre genre,
	             List<Actor> actors) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.duration = duration;
		this.language = language;
		this.genre = genre;
		this.actors = actors;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Movie movie = (Movie) o;
		return id.equals(movie.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
