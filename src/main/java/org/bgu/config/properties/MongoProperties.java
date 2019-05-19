package org.bgu.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ConfigurationProperties(prefix="mongodb")
public class MongoProperties {

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
		if (port == 0)
			return 27017;
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setPort(Integer port) { this.port = port; }

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
