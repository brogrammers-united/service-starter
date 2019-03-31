package org.bgu.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.bgu.model.oauth.helper.BguOAuth2UserInfoDeserializer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using=BguOAuth2UserInfoDeserializer.class)
public class GithubBguOAuth2UserInfo extends BguOAuth2UserInfo {

	public GithubBguOAuth2UserInfo(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public int getId() {
		return Integer.valueOf((String) attributes.getOrDefault("id", new Integer(-1)));
	}

	@Override
	public String getEmail() {
		return (String) attributes.get("email");
	}

	@Override
	public String getName() {
		return (String) attributes.get("name");
	}

	@Override
	public String getImageUrl() {
		return (String) attributes.get("avatar_url");
	}

	@Override
	public boolean isMfaEnabled() {
		return Boolean.valueOf((String)attributes.get("two_factor_authentication"));
	}

	@Override
	public String getUsername() {
		return (String) attributes.get("login");
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.stream(((String) attributes.get("scope")).split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
	}
	
	@Override
	public void addAuthorities(Collection<? extends GrantedAuthority> authorities) {
		StringBuilder scope = new StringBuilder().append((String) attributes.get("scope"));
		authorities.stream().map(GrantedAuthority::getAuthority).forEach(scope::append);
		attributes.put("scope", scope.toString());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GithubBguOAuth2UserInfo [getId()=");
		builder.append(getId());
		builder.append(", getEmail()=");
		builder.append(getEmail());
		builder.append(", getName()=");
		builder.append(getName());
		builder.append(", getImageUrl()=");
		builder.append(getImageUrl());
		builder.append(", isMfaEnabled()=");
		builder.append(isMfaEnabled());
		builder.append(", getUsername()=");
		builder.append(getUsername());
		builder.append("]");
		return builder.toString();
	}

	
	
}
