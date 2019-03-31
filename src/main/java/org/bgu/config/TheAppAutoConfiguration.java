package org.bgu.config;

import org.bgu.config.oauth.OAuth2Beans;
import org.bgu.oauth.service.ServiceConfiguration;
import org.bgu.repository.RepositoryConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = { MongoConfig.class, PropertiesConfiguration.class, WebConfig.class, WebSecurityConfig.class, MethodSecurityConfig.class,
		OAuth2Beans.class, ServiceConfiguration.class, RepositoryConfiguration.class })
public class TheAppAutoConfiguration {

}
