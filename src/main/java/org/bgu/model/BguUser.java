package org.bgu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bgu.model.interfaces.BguUserDetails;
import org.bgu.model.oauth.helper.LoginPreference;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

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

	public static BguUser generateUserFromOAuthInfo(String githubOAuthToken, BguOAuth2UserInfo userInfo) {
		GithubBguOAuth2UserInfo info = (GithubBguOAuth2UserInfo) userInfo;
		BguUser user = new BguUser(info.getUsername(), info.getEmail(), "ROLE_USER", true, true, true, true, info.getAttributes(), githubOAuthToken);
		return user;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BguUser bguUser = (BguUser) o;
		return enabled == bguUser.enabled &&
				accountNonLocked == bguUser.accountNonLocked &&
				accountNonExpired == bguUser.accountNonExpired &&
				credentialsNonExpired == bguUser.credentialsNonExpired &&
				Objects.equals(id, bguUser.id) &&
				Objects.equals(username, bguUser.username) &&
				Objects.equals(email, bguUser.email) &&
				Objects.equals(authorities, bguUser.authorities) &&
				Objects.equals(githubOAuthToken, bguUser.githubOAuthToken) &&
				Objects.equals(attributes, bguUser.attributes);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, username, email, authorities, enabled, accountNonLocked, accountNonExpired, credentialsNonExpired, githubOAuthToken, attributes);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", BguUser.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("username='" + username + "'")
				.add("email='" + email + "'")
				.add("authorities='" + authorities + "'")
				.add("enabled=" + enabled)
				.add("accountNonLocked=" + accountNonLocked)
				.add("accountNonExpired=" + accountNonExpired)
				.add("credentialsNonExpired=" + credentialsNonExpired)
				.add("githubOAuthToken='" + githubOAuthToken + "'")
				.add("attributes=" + attributes)
				.toString();
	}
}
