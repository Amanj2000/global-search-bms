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

	@Bean
	public CommandLineRunner commandLineRunner(MovieRepository movieRepository) {
		return args -> {
			saveMovies(movieRepository);
		};
	}

	private void saveMovies(MovieRepository movieRepository) {
		Actor shahRukhKhan = new Actor("Shah Rukh Khan");
		Actor johnAbraham = new Actor("John Abraham");
		Actor salmanKhan = new Actor("Salman Khan");
		Actor rajkumarRao = new Actor("Rajkumar Rao");
		Actor ayushmannKhurrana = new Actor("Ayushmann Khurrana");
		Actor akshayKumar = new Actor("Akshay Kumar");
		Actor nanaPatekar = new Actor("Nana Patekar");
		Actor anilKapoor = new Actor("Anil Kapoor");


		Actor deepikaPadukone = new Actor("Deepika Padukone");
		Actor katrinaKaif = new Actor("Katrina Kaif");
		Actor kritiSanon = new Actor("Kriti Sanon");

		Movie pathan = new Movie(
				1,
				"Pathan",
				"Pathaan is a 2023 Indian Hindi-language action thriller film directed by Siddharth Anand and produced by Aditya Chopra of Yash Raj Films.",
				146,
				"Hindi",
				Genre.ACTION,
				Arrays.asList(shahRukhKhan, salmanKhan, johnAbraham, deepikaPadukone)
		);

		Movie welcome = new Movie(
				2,
				"Welcome",
				"Welcome is an Indian 2007 Hindi-language comedy film written and directed by Anees Bazmee. ",
				159,
				"Hindi",
				Genre.COMEDY,
				Arrays.asList(akshayKumar, nanaPatekar, anilKapoor, katrinaKaif)
		);

		Movie ekThaTiger = new Movie(
				3,
				"Ek Tha Tiger",
				"Ek Tha Tiger is a 2012 Indian Hindi-language spy action-thriller film directed by Kabir Khan and written by Kabir and Neelesh Misra",
				132,
				"Hindi",
				Genre.ACTION,
				Arrays.asList(salmanKhan, katrinaKaif)
		);

		Movie bareillyKiBarfi = new Movie(
				4,
				"Bareilly Ki Barfi",
				"Bareilly Ki Barfi is a 2017 Indian Hindi-language romantic comedy film directed by Ashwiny Iyer Tiwari",
				122,
				"Hindi",
				Genre.COMEDY,
				Arrays.asList(rajkumarRao, ayushmannKhurrana, kritiSanon)
		);

		movieRepository.saveAll(Arrays.asList(pathan, welcome, ekThaTiger, bareillyKiBarfi));
	}
}
