package org.bgu.model.oauth;

import org.bgu.model.oauth.helper.SerializableObjectConverter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

@Document(collection="bgu_access_token")
public class BguAccessToken {

	@Id
	private ObjectId id;
	
	private String tokenId;

	private String authenticationId;

	private String username;

	private String clientId;

	private String authentication;

	private String refreshToken;

	private String token;
	
	public BguAccessToken() {}

	@PersistenceConstructor
	public BguAccessToken(String tokenId, String authenticationId, String username, String clientId,
			String authentication, String refreshToken, String token) {
		super();
		this.tokenId = tokenId;
		this.authenticationId = authenticationId;
		this.username = username;
		this.clientId = clientId;
		this.authentication = authentication;
		this.refreshToken = refreshToken;
		this.token = token;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
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

	public void setUsername(String username) {
		this.username = username;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public OAuth2AccessToken getToken() {
		return new DefaultOAuth2AccessToken(token);
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setToken(OAuth2AccessToken token) {
		this.token = token.getValue();
	}

	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}

	public OAuth2Authentication getAuthentication() {
		return SerializableObjectConverter.deserialize(authentication);
	}

	public void setAuthentication(OAuth2Authentication authentication) {
		this.authentication = SerializableObjectConverter.serialize(authentication);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccessToken [id=");
		builder.append(id);
		builder.append(", tokenId=");
		builder.append(tokenId);
		builder.append(", authenticationId=");
		builder.append(authenticationId);
		builder.append(", clientId=");
		builder.append(clientId);
		builder.append(", authentication=");
		builder.append(authentication);
		builder.append(", refreshToken=");
		builder.append(refreshToken);
		builder.append(", token=");
		builder.append(token);
		builder.append("]");
		return builder.toString();
	}
}
