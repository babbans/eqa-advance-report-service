package com.eqa.eqaadvancereportservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EqaAprSummaryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EqaAprSummaryServiceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		return modelMapper;
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
