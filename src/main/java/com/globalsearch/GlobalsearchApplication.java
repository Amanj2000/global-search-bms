package com.globalsearch;

import com.globalsearch.model.Actor;
import com.globalsearch.model.Movie;
import com.globalsearch.model.enums.Genre;
import com.globalsearch.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class GlobalsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlobalsearchApplication.class, args);
	}
}
