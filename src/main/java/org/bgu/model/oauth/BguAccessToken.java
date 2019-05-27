package org.bgu.model.oauth;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.Objects;

@Document(collection="bgu_access_token")
public class BguAccessToken {

	@Id
	private String tokenId;

	private String authenticationId;

	private String username;

	private String clientId;

	private byte[] authentication;

	private byte[] token;
	
	public BguAccessToken() {}

	@PersistenceConstructor
	public BguAccessToken(String tokenId, String authenticationId, String username, String clientId,
			byte[] authentication, byte[] token) {
		super();
		this.tokenId = tokenId;
		this.authenticationId = authenticationId;
		this.username = username;
		this.clientId = clientId;
		this.authentication = authentication;
		this.token = token;
	}


	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getTokenId() {
		return tokenId;
	}

	public String getAuthenticationId() {
		return authenticationId;
	}

	public void setAuthenticationId(String authenticationId) {
		this.authenticationId = authenticationId;
	}

	public String getUsername() {
		return username;
	}

	public String getClientId() {
		return clientId;
	}

	public byte[] getAuthentication() {
		return authentication;
	}

	public byte[] getToken() {
		return token;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BguAccessToken that = (BguAccessToken) o;
		return Objects.equals(tokenId, that.tokenId) &&
				Objects.equals(authenticationId, that.authenticationId) &&
				Objects.equals(username, that.username) &&
				Objects.equals(clientId, that.clientId) &&
				Arrays.equals(authentication, that.authentication) &&
				Arrays.equals(token, that.token);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(tokenId, authenticationId, username, clientId);
		result = 31 * result + Arrays.hashCode(authentication);
		result = 31 * result + Arrays.hashCode(token);
		return result;
	}
}
