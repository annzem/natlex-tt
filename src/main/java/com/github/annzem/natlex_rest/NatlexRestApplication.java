package com.github.annzem.natlex_rest;

import com.github.annzem.natlex_rest.model.GeoClass;
import com.github.annzem.natlex_rest.model.Section;
import com.github.annzem.natlex_rest.repository.GeoClassRepository;
import com.github.annzem.natlex_rest.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class NatlexRestApplication {

	@Autowired
	SectionRepository sectionRepository;

	@Autowired
	GeoClassRepository geoClassRepository;

	public static void main(String[] args) {
		SpringApplication.run(NatlexRestApplication.class, args);
	}




}
