package org.bgu.model.oauth;

import org.bgu.model.oauth.helper.SerializableObjectConverter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

@Document(collection="bgu_refresh_token")
public class BguRefreshToken {

	@Id
	private ObjectId id;
	
	private String tokenId;

	private String authentication;

	private String token;
	
	public BguRefreshToken() {}
	
	@PersistenceConstructor
	public BguRefreshToken(String tokenId, String authentication, String token) {
		super();
		this.tokenId = tokenId;
		this.authentication = authentication;
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

	public OAuth2RefreshToken getToken() {
		return new DefaultOAuth2RefreshToken(token);
	}

	public void setToken(OAuth2RefreshToken token) {
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
		builder.append("RefreshToken [id=");
		builder.append(id);
		builder.append(", tokenId=");
		builder.append(tokenId);
		builder.append(", authentication=");
		builder.append(authentication);
		builder.append(", token=");
		builder.append(token);
		builder.append("]");
		return builder.toString();
	}
	
	
}
