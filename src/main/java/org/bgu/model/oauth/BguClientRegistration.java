package org.bgu.model.oauth;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorizationGrantType == null) ? 0 : authorizationGrantType.hashCode());
		result = prime * result + ((authorizationUri == null) ? 0 : authorizationUri.hashCode());
		result = prime * result + ((clientId == null) ? 0 : clientId.hashCode());
		result = prime * result + ((clientName == null) ? 0 : clientName.hashCode());
		result = prime * result + ((clientSecret == null) ? 0 : clientSecret.hashCode());
		result = prime * result + ((configurationMetadata == null) ? 0 : configurationMetadata.hashCode());
		result = prime * result + ((jwkSetUri == null) ? 0 : jwkSetUri.hashCode());
		result = prime * result + ((redirectUriTemplate == null) ? 0 : redirectUriTemplate.hashCode());
		result = prime * result + ((registrationId == null) ? 0 : registrationId.hashCode());
		result = prime * result + ((scope == null) ? 0 : scope.hashCode());
		result = prime * result + ((tokenUri == null) ? 0 : tokenUri.hashCode());
		result = prime * result
				+ ((userInfoAuthenticationMethod == null) ? 0 : userInfoAuthenticationMethod.hashCode());
		result = prime * result + ((userInfoUri == null) ? 0 : userInfoUri.hashCode());
		result = prime * result + ((userNameAttributeName == null) ? 0 : userNameAttributeName.hashCode());
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
		BguClientRegistration other = (BguClientRegistration) obj;
		if (authorizationGrantType == null) {
			if (other.authorizationGrantType != null)
				return false;
		} else if (!authorizationGrantType.equals(other.authorizationGrantType))
			return false;
		if (authorizationUri == null) {
			if (other.authorizationUri != null)
				return false;
		} else if (!authorizationUri.equals(other.authorizationUri))
			return false;
		if (clientId == null) {
			if (other.clientId != null)
				return false;
		} else if (!clientId.equals(other.clientId))
			return false;
		if (clientName == null) {
			if (other.clientName != null)
				return false;
		} else if (!clientName.equals(other.clientName))
			return false;
		if (clientSecret == null) {
			if (other.clientSecret != null)
				return false;
		} else if (!clientSecret.equals(other.clientSecret))
			return false;
		if (configurationMetadata == null) {
			if (other.configurationMetadata != null)
				return false;
		} else if (!configurationMetadata.equals(other.configurationMetadata))
			return false;
		if (jwkSetUri == null) {
			if (other.jwkSetUri != null)
				return false;
		} else if (!jwkSetUri.equals(other.jwkSetUri))
			return false;
		if (redirectUriTemplate == null) {
			if (other.redirectUriTemplate != null)
				return false;
		} else if (!redirectUriTemplate.equals(other.redirectUriTemplate))
			return false;
		if (registrationId == null) {
			if (other.registrationId != null)
				return false;
		} else if (!registrationId.equals(other.registrationId))
			return false;
		if (scope == null) {
			if (other.scope != null)
				return false;
		} else if (!scope.equals(other.scope))
			return false;
		if (tokenUri == null) {
			if (other.tokenUri != null)
				return false;
		} else if (!tokenUri.equals(other.tokenUri))
			return false;
		if (userInfoAuthenticationMethod == null) {
			if (other.userInfoAuthenticationMethod != null)
				return false;
		} else if (!userInfoAuthenticationMethod.equals(other.userInfoAuthenticationMethod))
			return false;
		if (userInfoUri == null) {
			if (other.userInfoUri != null)
				return false;
		} else if (!userInfoUri.equals(other.userInfoUri))
			return false;
		if (userNameAttributeName == null) {
			if (other.userNameAttributeName != null)
				return false;
		} else if (!userNameAttributeName.equals(other.userNameAttributeName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ApplicationClientRegistration [id=");
		builder.append(id);
		builder.append(", registrationId=");
		builder.append(registrationId);
		builder.append(", clientName=");
		builder.append(clientName);
		builder.append(", clientId=");
		builder.append(clientId);
		builder.append(", clientSecret=");
		builder.append("[PROTECTED]");
		builder.append(", authorizationUri=");
		builder.append(authorizationUri);
		builder.append(", authorizationGrantType=");
		builder.append(authorizationGrantType);
		builder.append(", scope=");
		builder.append(scope);
		builder.append(", userInfoAuthenticationMethod=");
		builder.append(userInfoAuthenticationMethod);
		builder.append(", redirectUriTemplate=");
		builder.append(redirectUriTemplate);
		builder.append(", userInfoUri=");
		builder.append(userInfoUri);
		builder.append(", tokenUri=");
		builder.append(tokenUri);
		builder.append(", userNameAttributeName=");
		builder.append(userNameAttributeName);
		builder.append(", jwkSetUri=");
		builder.append(jwkSetUri);
		builder.append(", configurationMetadata=");
		builder.append(configurationMetadata);
		builder.append("]");
		return builder.toString();
	}

}
