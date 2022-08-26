package com.developeerscafe.mongodbpoc.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.developeerscafe.mongodbpoc.domain.MovieDomain;
import com.developeerscafe.mongodbpoc.model.Movie;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String>{
	
	@Query(value = "{ 'popularity' : { $gte : ?0, $lte : ?1 } }" , 
			fields = "{ cast: 0, popularity: 0 }")
	List<Movie> findByPopularityValue(Integer minPopularity, Integer maxPopularity);

}
