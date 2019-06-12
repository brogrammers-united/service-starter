package org.bgu.model.oauth;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.core.AuthenticationMethod;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Document(collection = "bgu_client_registration")
public class BguClientRegistration {

	@Id
	private ObjectId id;

	@Indexed(unique = true)
	private final String registrationId;

	private final String clientName;

	private final String clientId;

	private final String clientSecret;

	private final String authorizationUri;

	private final AuthorizationGrantType authorizationGrantType;

	private final String scope;

	private final AuthenticationMethod userInfoAuthenticationMethod;

	private final ClientAuthenticationMethod clientAuthenticationMethod;

	private final String redirectUriTemplate;

	private final String userInfoUri;

	private final String tokenUri;

	private final String userNameAttributeName;

	private final String jwkSetUri;

	private final Map<String, Object> configurationMetadata;

	@PersistenceConstructor
	public BguClientRegistration(String registrationId, String clientName, String clientId, String clientSecret,
			String authorizationUri, AuthorizationGrantType authorizationGrantType, String scope,
			AuthenticationMethod userInfoAuthenticationMethod, ClientAuthenticationMethod clientAuthenticationMethod,
			String redirectUriTemplate, String userInfoUri, String tokenUri, String userNameAttributeName,
			String jwkSetUri, Map<String, Object> configurationMetadata) {
		super();
		this.registrationId = registrationId;
		this.clientName = clientName;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.authorizationUri = authorizationUri;
		this.authorizationGrantType = authorizationGrantType;
		this.scope = scope;
		this.userInfoAuthenticationMethod = userInfoAuthenticationMethod;
		this.clientAuthenticationMethod = clientAuthenticationMethod;
		this.redirectUriTemplate = redirectUriTemplate;
		this.userInfoUri = userInfoUri;
		this.tokenUri = tokenUri;
		this.userNameAttributeName = StringUtils.hasText(userNameAttributeName) ? userNameAttributeName
				: IdTokenClaimNames.SUB;
		this.jwkSetUri = jwkSetUri;
		this.configurationMetadata = configurationMetadata;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public String getClientName() {
		return clientName;
	}

	public String getClientId() {
		return clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public String getAuthorizationUri() {
		return authorizationUri;
	}

	public AuthorizationGrantType getAuthorizationGrantType() {
		return authorizationGrantType;
	}

	public Set<String> getScope() {
		return Arrays.stream(scope.split(",")).collect(Collectors.toSet());
	}

	public AuthenticationMethod getUserInfoAuthenticationMethod() {
		return userInfoAuthenticationMethod;
	}

	public ClientAuthenticationMethod getClientAuthenticationMethod() {
		return clientAuthenticationMethod;
	}

	public String getRedirectUriTemplate() {
		return redirectUriTemplate;
	}

	public String getUserInfoUri() {
		return userInfoUri;
	}

	public String getTokenUri() {
		return tokenUri;
	}

	public String getUserNameAttributeName() {
		return userNameAttributeName;
	}

	public String getJwkSetUri() {
		return jwkSetUri;
	}

	public Map<String, Object> getConfigurationMetadata() {
		return configurationMetadata;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BguClientRegistration that = (BguClientRegistration) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(registrationId, that.registrationId) &&
				Objects.equals(clientName, that.clientName) &&
				Objects.equals(clientId, that.clientId) &&
				Objects.equals(clientSecret, that.clientSecret) &&
				Objects.equals(authorizationUri, that.authorizationUri) &&
				Objects.equals(authorizationGrantType, that.authorizationGrantType) &&
				Objects.equals(scope, that.scope) &&
				Objects.equals(userInfoAuthenticationMethod, that.userInfoAuthenticationMethod) &&
				Objects.equals(clientAuthenticationMethod, that.clientAuthenticationMethod) &&
				Objects.equals(redirectUriTemplate, that.redirectUriTemplate) &&
				Objects.equals(userInfoUri, that.userInfoUri) &&
				Objects.equals(tokenUri, that.tokenUri) &&
				Objects.equals(userNameAttributeName, that.userNameAttributeName) &&
				Objects.equals(jwkSetUri, that.jwkSetUri) &&
				Objects.equals(configurationMetadata, that.configurationMetadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, registrationId, clientName, clientId, clientSecret, authorizationUri, authorizationGrantType, scope, userInfoAuthenticationMethod, clientAuthenticationMethod, redirectUriTemplate, userInfoUri, tokenUri, userNameAttributeName, jwkSetUri, configurationMetadata);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", BguClientRegistration.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("registrationId='" + registrationId + "'")
				.add("clientName='" + clientName + "'")
				.add("clientId='" + clientId + "'")
				.add("clientSecret='" + clientSecret + "'")
				.add("authorizationUri='" + authorizationUri + "'")
				.add("authorizationGrantType=" + authorizationGrantType)
				.add("scope='" + scope + "'")
				.add("userInfoAuthenticationMethod=" + userInfoAuthenticationMethod)
				.add("clientAuthenticationMethod=" + clientAuthenticationMethod)
				.add("redirectUriTemplate='" + redirectUriTemplate + "'")
				.add("userInfoUri='" + userInfoUri + "'")
				.add("tokenUri='" + tokenUri + "'")
				.add("userNameAttributeName='" + userNameAttributeName + "'")
				.add("jwkSetUri='" + jwkSetUri + "'")
				.add("configurationMetadata=" + configurationMetadata)
				.toString();
	}
}
