package org.bgu.model.oauth;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.bgu.model.BguOAuth2UserInfo;
import org.bgu.model.GithubBguOAuth2UserInfo;
import org.bgu.model.UserAuthority;
import org.bgu.model.interfaces.BguUserDetails;
import org.bgu.model.oauth.helper.LoginPreference;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "bgu_user")
public class BguUser implements BguUserDetails, OAuth2User {

	private static final long serialVersionUID = 3L;

	@Id
	@JsonIgnore
	private ObjectId id;

	@Indexed(unique = true)
	private final String username;

	@Indexed(unique = true)
	private final String email;

	private final String authorities;

	@JsonIgnore
	private final boolean enabled;

	@JsonIgnore
	private final boolean accountNonLocked;

	@JsonIgnore
	private final boolean accountNonExpired;

	@JsonIgnore
	private final boolean credentialsNonExpired;

	private final String githubOAuthToken;

	private final Map<String, Object> attributes;
	
	@PersistenceConstructor
	public BguUser(String username, String email, String authorities, boolean enabled, boolean accountNonLocked,
			boolean accountNonExpired, boolean credentialsNonExpired, Map<String, Object> attributes,
			String githubOAuthToken) {
		super();
		this.username = username;
		this.authorities = authorities;
		this.email = email;
		this.enabled = enabled;
		this.accountNonLocked = accountNonLocked;
		this.accountNonExpired = accountNonExpired;
		this.credentialsNonExpired = credentialsNonExpired;
		this.attributes = attributes;
		this.githubOAuthToken = githubOAuthToken;
	}

	public String getUsername() {
		return (String) attributes.getOrDefault("login", username);
	}

	public String getPassword() {
		return "";
	}

	public String getEmail() {
		return (String) attributes.getOrDefault("email", email);
	}

	public Set<UserAuthority> getAuthorities() {
		return Arrays.stream(authorities.split(",")).map(UserAuthority::new).collect(Collectors.toSet());
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public String getName() {
		return (String) attributes.getOrDefault("name", "Not yet implemented");
	}

	public String getImageUrl() {
		return (String) attributes.getOrDefault("avatar_url", null);
	}

	public boolean isMfaEnabled() {
		return Boolean.valueOf((String) attributes.getOrDefault("two_factor_authentication", "false"));
	}

	public int getUserId() {
		return Integer.parseInt((String) attributes.getOrDefault("id", 0));
	}

	public String getGithubOAuthToken() {
		return githubOAuthToken;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}
	
	@Override
	public LoginPreference getLoginPreference() {
		if (StringUtils.hasText(getUsername()) && StringUtils.hasText(getEmail()))
			return LoginPreference.ANY;
		else if (!StringUtils.hasText(getUsername()) && StringUtils.hasText(getEmail()))
			return LoginPreference.EMAIL;
		else if (StringUtils.hasText(getUsername()) && !StringUtils.hasText(getEmail()))
			return LoginPreference.USERNAME;
		return LoginPreference.NONE;
	}

	public static BguUser generateUserFromOAuthInfo(String githubOAuthToken, BguUserDetails details, BguOAuth2UserInfo userInfo) {
		GithubBguOAuth2UserInfo info = (GithubBguOAuth2UserInfo) userInfo;
		BguUser user = new BguUser(details.getUsername(), details.getEmail(),
				details.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")),
				details.isEnabled(), details.isAccountNonLocked(), details.isAccountNonExpired(),
				details.isCredentialsNonExpired(), info.getAttributes(), githubOAuthToken);
		return user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (accountNonExpired ? 1231 : 1237);
		result = prime * result + (accountNonLocked ? 1231 : 1237);
		result = prime * result + ((attributes == null) ? 0 : attributes.hashCode());
		result = prime * result + ((authorities == null) ? 0 : authorities.hashCode());
		result = prime * result + (credentialsNonExpired ? 1231 : 1237);
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((githubOAuthToken == null) ? 0 : githubOAuthToken.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BguUser other = (BguUser) obj;
		if (accountNonExpired != other.accountNonExpired)
			return false;
		if (accountNonLocked != other.accountNonLocked)
			return false;
		if (attributes == null) {
			if (other.attributes != null)
				return false;
		} else if (!attributes.equals(other.attributes))
			return false;
		if (authorities == null) {
			if (other.authorities != null)
				return false;
		} else if (!authorities.equals(other.authorities))
			return false;
		if (credentialsNonExpired != other.credentialsNonExpired)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enabled != other.enabled)
			return false;
		if (githubOAuthToken == null) {
			if (other.githubOAuthToken != null)
				return false;
		} else if (!githubOAuthToken.equals(other.githubOAuthToken))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BguUser [authorities=");
		builder.append(authorities);
		builder.append(", enabled=");
		builder.append(enabled);
		builder.append(", accountNonLocked=");
		builder.append(accountNonLocked);
		builder.append(", accountNonExpired=");
		builder.append(accountNonExpired);
		builder.append(", credentialsNonExpired=");
		builder.append(credentialsNonExpired);
		builder.append(", githubOAuthToken=");
		builder.append(githubOAuthToken);
		builder.append(", getUsername()=");
		builder.append(getUsername());
		builder.append(", getEmail()=");
		builder.append(getEmail());
		builder.append(", getName()=");
		builder.append(getName());
		builder.append(", getImageUrl()=");
		builder.append(getImageUrl());
		builder.append(", isMfaEnabled()=");
		builder.append(isMfaEnabled());
		builder.append(", getUserId()=");
		builder.append(getUserId());
		builder.append("]");
		return builder.toString();
	}

}
