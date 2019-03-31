package org.bgu.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.fasterxml.jackson.annotation.JsonIgnore;

//@JsonTypeInfo(
//	use = JsonTypeInfo.Id.CLASS,
//	include = JsonTypeInfo.As.PROPERTY,
//	property = "class"
//)
//@JsonSubTypes({
//	@JsonSubTypes.Type(value=GithubBguOAuth2UserInfo.class, name="github")
//})
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
	
	public abstract void addAuthorities(Collection<? extends GrantedAuthority> authorities);
}
