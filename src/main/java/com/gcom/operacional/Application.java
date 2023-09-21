package com.gcom.operacional;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.gcom.operacional.config.SecurityConfig;

@SpringBootApplication
@ImportAutoConfiguration({ SecurityConfig.class })
@ComponentScan(basePackages = { "com.gcom.operacional" })
@EntityScan(basePackages = { "com.gcom.operacional.entity" })
@EnableJpaRepositories(basePackages = { "com.gcom.operacional" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {
	    return jacksonObjectMapperBuilder -> 
	        jacksonObjectMapperBuilder.timeZone(TimeZone.getDefault());
	}
	
	@PostConstruct
	void started() {
	    TimeZone.setDefault(TimeZone.getTimeZone("Chile/Continental"));
	}

}
