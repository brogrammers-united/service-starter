package org.bgu.model.oauth;

import java.util.Set;

import org.springframework.security.oauth2.client.registration.ClientRegistration;

public class BguClientRegistrationDetails {

	private final String clientId;
	private final String clientSecret;
	private final Set<String> scope;

	public BguClientRegistrationDetails(final ClientRegistration registration) {
		this.clientId = registration.getClientId();
		this.clientSecret = registration.getClientSecret();
		this.scope = registration.getScopes();
	}

	public Set<String> getScope() {
		return scope;
	}

	public String getClientId() {
		return clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clientId == null) ? 0 : clientId.hashCode());
		result = prime * result + ((clientSecret == null) ? 0 : clientSecret.hashCode());
		result = prime * result + ((scope == null) ? 0 : scope.hashCode());
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
		BguClientRegistrationDetails other = (BguClientRegistrationDetails) obj;
		if (clientId == null) {
			if (other.clientId != null)
				return false;
		} else if (!clientId.equals(other.clientId))
			return false;
		if (clientSecret == null) {
			if (other.clientSecret != null)
				return false;
		} else if (!clientSecret.equals(other.clientSecret))
			return false;
		if (scope == null) {
			if (other.scope != null)
				return false;
		} else if (!scope.equals(other.scope))
			return false;
		return true;
	}

}
