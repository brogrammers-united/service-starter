package org.bgu.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.bgu.config.properties.ApplicationMongoProperties;
import org.bgu.config.properties.MongoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.Collection;

@Configuration
public class MongoConfig {
	
	@Autowired
	private MongoProperties mongoProperties;

	@Autowired
	private ApplicationMongoProperties applicationMongoProperties;

	@Bean("oauthClient")
	@Primary
	public MongoClient mongoClient() {
		MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
		builder.alwaysUseMBeans(mongoProperties.isRegisterMbeans());
		builder.sslEnabled(false);
		builder.connectionsPerHost(mongoProperties.getConnectionsPerHost());
		builder.connectTimeout(mongoProperties.getConnectionTimeout());
		builder.minConnectionsPerHost(mongoProperties.getMinConnectionsPerHost());
		builder.maxConnectionLifeTime(mongoProperties.getConnectionLifeTime());
		builder.maxConnectionIdleTime(mongoProperties.getConnectionIdleTime());
		return new MongoClient(new ServerAddress(mongoProperties.getUrl(), mongoProperties.getPort()), MongoCredential.createCredential(mongoProperties.getUsername(), mongoProperties.getDatabase(), mongoProperties.getPassword()), builder.build());
	}
	
	@Bean("oauthMongoDbFactory")
	@Primary
	public MongoDbFactory mongoDbFactory() {
		return new SimpleMongoDbFactory(mongoClient(), getOAuthDatabaseName());
	}

	@Primary
	@Bean("oauthMongoTemplate")
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongoDbFactory());
	}

	protected String getOAuthDatabaseName() {
		return mongoProperties.getDatabase();
	}

	protected Collection<String> getOAuthMappingBasePackages() {
		return mongoProperties.getMappingBasePackages();
	}

	@Bean("applicationMongoClient")
	public MongoClient applicationMongoClient() {
		MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
		builder.alwaysUseMBeans(applicationMongoProperties.isRegisterMbeans());
		builder.sslEnabled(false);
		builder.connectionsPerHost(applicationMongoProperties.getConnectionsPerHost());
		builder.connectTimeout(applicationMongoProperties.getConnectionTimeout());
		builder.minConnectionsPerHost(applicationMongoProperties.getMinConnectionsPerHost());
		builder.maxConnectionLifeTime(applicationMongoProperties.getConnectionLifeTime());
		builder.maxConnectionIdleTime(applicationMongoProperties.getConnectionIdleTime());
		return new MongoClient(new ServerAddress(applicationMongoProperties.getUrl(), applicationMongoProperties.getPort()), MongoCredential.createCredential(applicationMongoProperties.getUsername(), applicationMongoProperties.getDatabase(), applicationMongoProperties.getPassword()), builder.build());
	}

	@Bean("applicationMongoDbFactory")
	public MongoDbFactory applicationMongoDbFactory() { return new SimpleMongoDbFactory(applicationMongoClient(), getApplicationDatabaseName()); }

	@Bean("applicationMongoTemplate")
	public MongoTemplate applicationMongoTemplate() { return new MongoTemplate(applicationMongoDbFactory()); }

	protected String getApplicationDatabaseName() { return applicationMongoProperties.getDatabase(); }

	protected Collection<String> getApplicationMappingBasePackages() { return applicationMongoProperties.getMappingBasePackages(); }
}
