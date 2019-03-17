package org.bgu.config;

import org.bgu.config.properties.KeyStoreProperties;
import org.bgu.config.properties.MailProperties;
import org.bgu.config.properties.MongoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableConfigurationProperties({MongoProperties.class, MailProperties.class, KeyStoreProperties.class})
public class PropertiesConfiguration {
	
	@Autowired
	private ApplicationContext context;

	@Bean(name= {"mongoProperties", "mongoProps"})
	@Primary
	public MongoProperties mongoProperties() {
		return new MongoProperties(context);
	}
	
	@Bean(name= {"mailProperties", "mailProps"})
	@Primary
	public MailProperties mailProperties() {
		return new MailProperties(context);
	}
	
	@Bean(name= {"keyStoreProperties", "keyStoreProps"})
	@Primary
	public KeyStoreProperties keyStoreProperties() {
		return new KeyStoreProperties(context);
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		configurer.setLocations(new ClassPathResource("application.properties"), new ClassPathResource("application.yml"), new ClassPathResource("mongodb.properties"), new ClassPathResource("mongodb.yml"), new ClassPathResource("mail.properties"), new ClassPathResource("mail.yml"), new ClassPathResource("keystore.properties"), new ClassPathResource("keystore.yml"));
		configurer.setIgnoreResourceNotFound(true);
		configurer.setIgnoreUnresolvablePlaceholders(true); 
		configurer.setLocalOverride(true);
		return configurer;
	}
}
