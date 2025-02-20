package com.example.dcim.config;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@Getter
public class CategoryConfig {

	private final Set<String> allowedCategories;

	public CategoryConfig(@Value("${app.allowed-categories}") String categories) {
		this.allowedCategories = Stream.of(categories.split(",")).map(String::trim).collect(Collectors.toSet());
	}

}
