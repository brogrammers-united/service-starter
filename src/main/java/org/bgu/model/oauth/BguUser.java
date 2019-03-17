package org.bgu.model.oauth;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.bgu.model.BguRegistrationProvider;
import org.bgu.model.RegistrationProvider;
import org.bgu.model.UserAuthority;
import org.bgu.model.interfaces.BguUserDetails;
import org.bgu.model.interfaces.Verifiable;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "bgu_user")
public class BguUser implements OAuth2User, Verifiable, BguUserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	private ObjectId id;

	@Indexed(unique = true)
	private String username;

	@JsonIgnore
	private String password;

	@Indexed(unique = true)
	private String email;

	private String authorities;

	private String name;

	private String imageUrl;

	@JsonIgnore
	private boolean enabled;

	@JsonIgnore
	private boolean accountNonLocked;

	@JsonIgnore
	private boolean accountNonExpired;

	@JsonIgnore
	private boolean credentialsNonExpired;

	private Map<String, Object> attributes;

	private boolean mfaEnabled;

	@JsonIgnore
	private final BguRegistrationProvider registrationProvider;

	@PersistenceConstructor
	public BguUser(String username, String password, String authorities, String name, String email,
			boolean enabled, boolean accountNonLocked, boolean accountNonExpired, boolean credentialsNonExpired,
			Map<String, Object> attributes, boolean mfaEnabled, BguRegistrationProvider registrationProvider) {
		super();
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.name = name;
		this.email = email;
		this.enabled = enabled;
		this.accountNonLocked = accountNonLocked;
		this.accountNonExpired = accountNonExpired;
		this.credentialsNonExpired = credentialsNonExpired;
		this.attributes = attributes;
		this.mfaEnabled = mfaEnabled;
		this.registrationProvider = registrationProvider;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<UserAuthority> getAuthorities() {
		return Arrays.stream(authorities.split(",")).map(UserAuthority::new).collect(Collectors.toSet());
	}

	public void setAuthorities(Collection<UserAuthority> authorities) {
		this.authorities = authorities.stream().map(UserAuthority::getAuthority).collect(Collectors.joining(","));
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isMfaEnabled() {
		return mfaEnabled;
	}

	public void setMfaEnabled(boolean mfaEnabled) {
		this.mfaEnabled = mfaEnabled;
	}
	
	public RegistrationProvider getRegistrationProvider() {
		return registrationProvider;
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
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + (mfaEnabled ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		if (mfaEnabled != other.mfaEnabled)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
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
		builder.append("ApplicationUser [username=");
		builder.append(username);
		builder.append(", email=");
		builder.append(email);
		builder.append(", authorities=");
		builder.append(authorities);
		builder.append(", name=");
		builder.append(name);
		builder.append(", imageUrl=");
		builder.append(imageUrl);
		builder.append(", enabled=");
		builder.append(enabled);
		builder.append(", accountNonLocked=");
		builder.append(accountNonLocked);
		builder.append(", accountNonExpired=");
		builder.append(accountNonExpired);
		builder.append(", credentialsNonExpired=");
		builder.append(credentialsNonExpired);
		builder.append(", attributes=");
		builder.append(attributes);
		builder.append(", mfaEnabled=");
		builder.append(mfaEnabled);
		builder.append("]");
		return builder.toString();
	}

}
