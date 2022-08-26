package com.developeerscafe.mongodbpoc.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.developeerscafe.mongodbpoc.domain.MovieDomain;
import com.developeerscafe.mongodbpoc.model.Movie;
import com.developeerscafe.mongodbpoc.service.MovieService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class MovieController {

	private final MovieService movieService;

	@GetMapping("/message")
	public String displayRunningMessage() {
		return "Hello !! Good Morning";
	}

	@PostMapping("/saveMovie")
	public ResponseEntity<String> saveNewMovie(@RequestBody MovieDomain movieDomain) {
		Integer responseCode = movieService.saveNewMovie(movieDomain);

		if (responseCode.equals(201))
			return new ResponseEntity<>(HttpStatus.CREATED);

		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	@GetMapping("/getMovieByPopularity")
	public ResponseEntity<List<Movie>> getMovieByPopularity(@RequestParam Integer minPopularity,
			@RequestParam Integer maxPopularity) {
		
		return new ResponseEntity<List<Movie>>(movieService.getMovieByPopularity(minPopularity, maxPopularity),
				HttpStatus.FOUND);
	}

}
