package org.bgu.model.oauth;

import org.bgu.model.interfaces.BguUserDetails;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Objects;
import java.util.StringJoiner;

public class BguOAuth2AuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 1L;

	private final Object details;
	private final Object credentials;
	private final boolean authenticated;

	public BguOAuth2AuthenticationToken(BguUserDetails userDetails) {
		super(userDetails.getAuthorities());
		this.details = userDetails;
		this.credentials = "";
		this.authenticated = true;
	}

	@Override
	public Object getCredentials() {
		return credentials;
	}

	@Override
	public Object getPrincipal() {
		return details;
	}

	@Override
	public boolean isAuthenticated() {
		return this.authenticated;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		BguOAuth2AuthenticationToken that = (BguOAuth2AuthenticationToken) o;
		return authenticated == that.authenticated &&
				Objects.equals(details, that.details) &&
				Objects.equals(credentials, that.credentials);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), details, credentials, authenticated);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", BguOAuth2AuthenticationToken.class.getSimpleName() + "[", "]")
				.add("details=" + details)
				.add("credentials=" + credentials)
				.add("authenticated=" + authenticated)
				.toString();
	}
}
