package org.bgu.config;

import org.bgu.config.properties.ApplicationMongoProperties;
import org.bgu.config.properties.KeyStoreProperties;
import org.bgu.config.properties.MongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({MongoProperties.class, ApplicationMongoProperties.class, KeyStoreProperties.class})
public class PropertiesConfiguration {
}
