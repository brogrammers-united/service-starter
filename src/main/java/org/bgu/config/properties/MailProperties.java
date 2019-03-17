package org.bgu.config.properties;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;

@ConfigurationProperties(prefix="mail")
@PropertySource(value = { "classpath:application.properties", "classpath:application.yml", "classpath:mail.properties","classpath:mail.yml" }, ignoreResourceNotFound = false)
public class MailProperties {

	private final ApplicationContext context;
	
	private String username;
	private String password;
	private String fromAddress;
	private String subjectLine;
	
	public MailProperties(ApplicationContext context) {
		this.context = context;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getSubjectLine() {
		return subjectLine;
	}

	public void setSubjectLine(String subjectLine) {
		this.subjectLine = subjectLine;
	}
	
	@PostConstruct
	public void initProperties() {
		this.setUsername(context.getEnvironment().getProperty("mail.username"));
		this.setPassword(context.getEnvironment().getProperty("mail.password"));
		this.setSubjectLine(context.getEnvironment().getProperty("mail.subject-line", "Email Verification"));
		this.setFromAddress(context.getEnvironment().getProperty("mail.from-address"));
	}
}
