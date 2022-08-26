package com.developeerscafe.mongodbpoc.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.developeerscafe.mongodbpoc.domain.MovieDomain;
import com.developeerscafe.mongodbpoc.model.Movie;
import com.developeerscafe.mongodbpoc.repository.MovieRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class MovieService {

	private final MovieRepository movieRepository;

	public Integer saveNewMovie(MovieDomain movieDomain) {
		
		try {
			
			Movie movie = Movie.builder()
					.name(movieDomain.getName())
					.genre(movieDomain.getGenre())
					.cast(movieDomain.getCast())
					.runtime(movieDomain.getRuntime())
					.popularity(movieDomain.getPopularity())
					.build();
			
			movieRepository.save(movie);
			
		} catch (Exception e) {
			log.error("ERROR MSG :: Unable to save movie " + e.getMessage());
			return 500;
		}
		return 201;
	}

	public List<Movie> getMovieByPopularity(Integer minPopularity, Integer maxPopularity) {
		
		return movieRepository.findByPopularityValue(minPopularity, maxPopularity);
	}

}
