package org.bgu.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.bgu.config.properties.MongoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;

@EnableMongoRepositories(basePackages = "org.bgu.repository", mongoTemplateRef = "oauthMongoTemplate")
@Configuration
public class MongoConfig {
	
	@Autowired
	private MongoProperties mongoProperties;

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
		return new SimpleMongoDbFactory(mongoClient(), getDatabaseName());
	}

	@Primary
	@Bean("oauthMongoTemplate")
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongoDbFactory());
	}

	protected String getDatabaseName() {
		return mongoProperties.getDatabase();
	}

	protected Collection<String> getOAuthMappingBasePackages() {
		return mongoProperties.getMappingBasePackages();
	}
}
