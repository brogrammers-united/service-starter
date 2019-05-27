package org.bgu.config;

import org.bgu.config.oauth.OAuth2Beans;
import org.bgu.oauth.service.OAuth2ServiceBeans;
import org.bgu.repository.RepositoryBeans;
import org.bgu.security.SecurityBeans;
import org.bgu.web.TestController;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import(value = {MongoConfig.class, WebConfig.class, WebSecurityConfig.class, PropertiesConfiguration.class, SecurityBeans.class,
        OAuth2Beans.class, OAuth2ServiceBeans.class, RepositoryBeans.class, TestController.class})
public class TheAppAutoConfiguration {
}
