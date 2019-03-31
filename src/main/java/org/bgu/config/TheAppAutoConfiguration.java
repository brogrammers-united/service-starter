package org.bgu.config;

import org.bgu.config.oauth.OAuth2Beans;
import org.bgu.config.oauth.ResourceServerConfiguration;
import org.bgu.oauth.service.OAuth2ServiceBeans;
import org.bgu.repository.RepositoryBeans;
import org.bgu.security.SecurityBeans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = { ResourceServerConfiguration.class, MongoConfig.class, PropertiesConfiguration.class, WebConfig.class, WebSecurityConfig.class, MethodSecurityConfig.class,
		OAuth2Beans.class, OAuth2ServiceBeans.class, RepositoryBeans.class, SecurityBeans.class })
public class TheAppAutoConfiguration {

}
