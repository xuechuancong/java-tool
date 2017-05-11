package org.jeff.javatool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootConfig {

	@Bean
	public SpringBootContextHolder springContextHolder() {
		return new SpringBootContextHolder();
	}

}