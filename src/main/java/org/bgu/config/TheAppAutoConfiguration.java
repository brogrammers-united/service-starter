package org.bgu.config;

import org.bgu.config.oauth.OAuth2Beans;
import org.bgu.repository.RepositoryConfiguration;
import org.bgu.service.oauth.ServiceConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = { MongoConfig.class, PropertiesConfiguration.class, WebConfig.class, WebSecurityConfig.class,
		OAuth2Beans.class, ServiceConfiguration.class, RepositoryConfiguration.class })
public class TheAppAutoConfiguration {

}
