package org.bgu.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author William Gentry
 */
@ConfigurationProperties(prefix = "webclient")
public class WebClientProperties {

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
