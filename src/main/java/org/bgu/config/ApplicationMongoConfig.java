package org.bgu.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.bgu.config.annotation.RequiresMongoInclusion;
import org.bgu.config.properties.ApplicationMongoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.Collection;

/**
 * @author William Gentry
 */
@RequiresMongoInclusion
@Configuration
public class ApplicationMongoConfig {

    @Autowired
    private ApplicationMongoProperties applicationMongoProperties;

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
