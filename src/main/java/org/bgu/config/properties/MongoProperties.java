package org.bgu.config.properties;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringUtils;

@ConfigurationProperties(prefix="mongodb")
@PropertySource(value = { "classpath:mongodb.properties", "classpath:application.properties", "classpath:application.yml","classpath:mongodb.yml" }, ignoreResourceNotFound = false)
public class MongoProperties {

	private ApplicationContext context;
	
	private String url;
	private int port;
	private String username;
	private char[] password;
	private String database;
	private String mappingBasePackages;
	private boolean registerMbeans;
	private int minConnectionsPerHost;
	private int connectionsPerHost;
	private int connectionTimeout;
	private int connectionIdleTime;
	private int connectionLifeTime;

	public MongoProperties(ApplicationContext context) {
		this.context = context;
	}
	
	@PostConstruct
	public void initProperties() {
		this.setUrl(context.getEnvironment().getProperty("mongodb.url", "127.0.0.1"));
		this.setPort(Integer.valueOf(context.getEnvironment().getProperty("mongodb.port", "27017")));
		this.setUsername(context.getEnvironment().getProperty("mongodb.username"));
		this.setPassword(context.getEnvironment().getProperty("mongodb.password"));
		this.setDatabase(context.getEnvironment().getProperty("mongodb.database"));
		this.setRegisterMbeans(Boolean.valueOf(context.getEnvironment().getProperty("mongodb.register-mbeans", "true")));
		this.setConnectionsPerHost(Integer.valueOf(context.getEnvironment().getProperty("mongodb.max-connections-per-host", "100")));
		this.setMinConnectionsPerHost(Integer.valueOf(context.getEnvironment().getProperty("mongodb.min-connections-per-host", "0")));
		this.setConnectionLifeTime(Integer.valueOf(context.getEnvironment().getProperty("mongodb.connection-life-time", "0")));
		this.setConnectionIdleTime(Integer.valueOf(context.getEnvironment().getProperty("mongodb.connection-idle-time", "0")));
		this.setConnectionTimeout(Integer.valueOf(context.getEnvironment().getProperty("mongodb.connection-timeout", "0")));
	}
	
	
	
	public List<String> getMappingBasePackages() {
		return Arrays.stream(mappingBasePackages.split(",")).collect(Collectors.toList());
	}

	public void setMappingBasePackages(String mappingBasePackages) {
		this.mappingBasePackages = mappingBasePackages;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getMinConnectionsPerHost() {
		return minConnectionsPerHost;
	}

	public void setMinConnectionsPerHost(int minConnectionsPerHost) {
		this.minConnectionsPerHost = minConnectionsPerHost;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = StringUtils.hasText(password) ? password.toCharArray(): null; 
	}

	public boolean isRegisterMbeans() {
		return registerMbeans;
	}

	public void setRegisterMbeans(boolean registerMbeans) {
		this.registerMbeans = registerMbeans;
	}

	public int getConnectionsPerHost() {
		return connectionsPerHost;
	}

	public void setConnectionsPerHost(int connectionsPerHost) {
		this.connectionsPerHost = connectionsPerHost;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getConnectionIdleTime() {
		return connectionIdleTime;
	}

	public void setConnectionIdleTime(int connectionIdleTime) {
		this.connectionIdleTime = connectionIdleTime;
	}

	public int getConnectionLifeTime() {
		return connectionLifeTime;
	}

	public void setConnectionLifeTime(int connectionLifeTime) {
		this.connectionLifeTime = connectionLifeTime;
	}
	
	

}
