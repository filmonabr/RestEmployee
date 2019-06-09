package edu.mum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class RestEmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestEmployeeApplication.class, args);
	}

	@Bean
	ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("errorMessages", "messages");
		return messageSource;
	}

	@Bean
	MessageSourceAccessor message() {
		return new MessageSourceAccessor(messageSource());
	}
}
