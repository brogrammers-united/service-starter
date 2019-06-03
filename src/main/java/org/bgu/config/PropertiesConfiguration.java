package org.bgu.config;

import org.bgu.config.properties.KeyStoreProperties;
import org.bgu.config.properties.MongoProperties;
import org.bgu.config.properties.WebClientProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({MongoProperties.class, KeyStoreProperties.class, WebClientProperties.class})
public class PropertiesConfiguration {
}
