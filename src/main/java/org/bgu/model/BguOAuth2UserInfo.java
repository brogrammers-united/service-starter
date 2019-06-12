package org.bgu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.HashMap;
import java.util.Map;

public abstract class BguOAuth2UserInfo implements OAuth2User {

	protected final Map<String, Object> attributes;
	
	public BguOAuth2UserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	@JsonIgnore
	public Map<String, Object> getAttributes() {
		return new HashMap<>(attributes);
	}
	
	public abstract int getId();
	
	public abstract String getEmail();
	
	public abstract String getName();
	
	public abstract String getUsername();
	
	public abstract String getImageUrl();
	
	public abstract boolean isMfaEnabled();
	
}
