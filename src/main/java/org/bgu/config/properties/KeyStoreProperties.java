package org.bgu.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@ConfigurationProperties(prefix = "keystore")
public class KeyStoreProperties {

	private String type;
	private String fileName;
	private String alias;
	private char[] password;

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

	public void setPassword(String password) {
		if (!StringUtils.hasText(password)) {
			this.password = null;
			return;
		}
		this.password = password.toCharArray();
	}

}
