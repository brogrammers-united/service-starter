package org.bgu.config.properties;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;

@ConfigurationProperties(prefix = "keystore")
@PropertySource(value = { "classpath:application.properties", "classpath:application.yml",
		"classpath:keystore.properties", "classpath:keystore.yml" }, ignoreResourceNotFound = false)
public class KeyStoreProperties {

	private final ApplicationContext context;

	private String type;
	private String fileName;
	private String alias;
	private char[] password;

	public KeyStoreProperties(ApplicationContext context) {
		this.context = context;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		if (password.length == 0) {
			this.password = null;
			return;
		}
		this.password = password;
	}

	@PostConstruct
	public void initProperties() {
		this.setAlias(context.getEnvironment().getProperty("keystore.alias"));
		this.setFileName(context.getEnvironment().getProperty("keystore.file-name"));
		this.setType(context.getEnvironment().getProperty("keystore.type"));
		this.setPassword(context.getEnvironment().getProperty("keystore.password").toCharArray());
	}
}
