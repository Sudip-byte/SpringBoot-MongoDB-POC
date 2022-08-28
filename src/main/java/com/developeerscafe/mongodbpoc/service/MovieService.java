package com.developeerscafe.mongodbpoc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
	private final MongoTemplate mongoTemplate;

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

	public Page<Movie> getMovies(String name, Pageable pageable) {
		
		Query query = new Query().with(pageable);
		List<Criteria> criteria = new ArrayList<>();
		
		if(name != null && !name.isEmpty()) {
			criteria.add(Criteria.where("name").regex(name, "i"));
		}
		
		if(!criteria.isEmpty())
		{
			query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
		}
		
		Page<Movie> movies = PageableExecutionUtils.getPage(mongoTemplate.find(query, Movie.class),
				pageable,
				() -> mongoTemplate.count(query.skip(0).limit(0), Movie.class));
		
		return movies;
	}

}
