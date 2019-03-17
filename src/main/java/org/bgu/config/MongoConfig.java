package org.bgu.config;

import java.util.Collection;

import org.bgu.config.properties.MongoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {
	
	@Autowired
	private MongoProperties mongoProps;
	
	@Bean
	@Override
	public MongoClient mongoClient() {
		MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
		builder.alwaysUseMBeans(mongoProps.isRegisterMbeans());
		builder.sslEnabled(false);
		builder.connectionsPerHost(mongoProps.getConnectionsPerHost());
		builder.connectTimeout(mongoProps.getConnectionTimeout());
		builder.minConnectionsPerHost(mongoProps.getMinConnectionsPerHost());
		builder.maxConnectionLifeTime(mongoProps.getConnectionLifeTime());
		builder.maxConnectionIdleTime(mongoProps.getConnectionIdleTime());
		return new MongoClient(new ServerAddress(mongoProps.getUrl(), mongoProps.getPort()), MongoCredential.createCredential(mongoProps.getUsername(), mongoProps.getDatabase(), mongoProps.getPassword()), builder.build());
	}
	
	@Bean
	@Override
	public MongoDbFactory mongoDbFactory() {
		return new SimpleMongoDbFactory(mongoClient(), getDatabaseName());
	}
	
	@Bean
	@Override
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongoDbFactory());
	}

	@Override
	protected String getDatabaseName() {
		return mongoProps.getDatabase();
	}

	@Override
	public Collection<String> getMappingBasePackages() {
		return mongoProps.getMappingBasePackages();
	}
}
