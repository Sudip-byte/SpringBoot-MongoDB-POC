package com.developeerscafe.mongodbpoc.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(content = Include.NON_NULL)
public class MovieDomain {
	
	private String name;
	private List<String> genre;
	private List<String> cast;
	private String runtime;
	private Integer popularity;

}
