package com.developeerscafe.mongodbpoc.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Document
@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class Movie {
	
	@Id
	private String id;
	private String name;
	private List<String> genre;
	private List<String> cast;
	private String runtime;
	private Integer popularity;

}
