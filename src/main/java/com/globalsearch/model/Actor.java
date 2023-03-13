package com.globalsearch.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@NoArgsConstructor
@Document(indexName = "actors")
public class Actor {
	@Field(type = FieldType.Text)
	private String name;

	public Actor(String name) {
		this.name = name;
	}
}
