package com.huehnerschulte.raffael.hostablechat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@SpringBootApplication
public class HostablechatApplication {


	private final ThymeleafProperties properties;

	@Value("${spring.thymeleaf.templates_root:}")
	private String templatesRoot;

	public HostablechatApplication(ThymeleafProperties properties) {
		this.properties = properties;
	}

	public static void main(String[] args) {
		SpringApplication.run(HostablechatApplication.class, args);
	}

	@Bean
	public ITemplateResolver defaultTemplateResolver() {
		FileTemplateResolver resolver = new FileTemplateResolver();
		resolver.setSuffix(properties.getSuffix());
		resolver.setPrefix(templatesRoot);
		resolver.setTemplateMode(properties.getMode());
		resolver.setCacheable(properties.isCache());
		return resolver;
	}
}
