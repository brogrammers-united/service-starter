package org.bgu.model.oauth;

import org.bgu.model.ClientAuthority;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;
import java.util.stream.Collectors;

@Document(collection = "bgu_client_details")
public class BguClientDetails implements ClientDetails {

	private static final long serialVersionUID = 1L;

	@Id
	private ObjectId id;

	@Indexed(unique = true)
	private final String clientId;

	@Indexed(unique = true)
	private final String registrationId;

	private final String clientName;

	private final String clientSecret;

	private final String resourceIds;

	private final String scope;

	private final String authorizedGrantTypes;

	private final String registeredRedirectUri;

	private final String authorities;

	private final int accessTokenValidity;

	private final int refreshTokenValidity;

	private final Map<String, Object> additionalInformation;

	@PersistenceConstructor
	public BguClientDetails(String clientId, String registrationId, String clientName, String clientSecret,
			String resourceIds, String scope, String authorizedGrantTypes, String registeredRedirectUri,
			String authorities, int accessTokenValidity, int refreshTokenValidity,
			Map<String, Object> additionalInformation) {
		super();
		this.clientId = clientId;
		this.registrationId = registrationId;
		this.clientName = clientName;
		this.clientSecret = clientSecret;
		this.resourceIds = resourceIds;
		this.scope = scope;
		this.authorizedGrantTypes = authorizedGrantTypes;
		this.registeredRedirectUri = registeredRedirectUri;
		this.authorities = authorities;
		this.accessTokenValidity = accessTokenValidity;
		this.refreshTokenValidity = refreshTokenValidity;
		this.additionalInformation = additionalInformation;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public String getClientName() {
		return clientName;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public Set<String> getResourceIds() {
		return Arrays.stream(resourceIds.split(",")).collect(Collectors.toSet());
	}

	public Set<String> getScope() {
		return Arrays.stream(scope.split(",")).collect(Collectors.toSet());
	}

	public Set<String> getAuthorizedGrantTypes() {
		return Arrays.stream(authorizedGrantTypes.split(",")).collect(Collectors.toSet());
	}

	public Set<String> getRegisteredRedirectUri() {
		return Arrays.stream(registeredRedirectUri.split(",")).collect(Collectors.toSet());
	}

	public Collection<GrantedAuthority> getAuthorities() {
		return Arrays.stream(authorities.split(",")).map(ClientAuthority::new).collect(Collectors.toSet());
	}

	public Integer getAccessTokenValiditySeconds() {
		return accessTokenValidity;
	}

	public Integer getRefreshTokenValiditySeconds() {
		return refreshTokenValidity;
	}

	public Map<String, Object> getAdditionalInformation() {
		return additionalInformation;
	}
	
	public boolean isSecretRequired() {
		return true;
	}
	
	public boolean isScoped() {
		return true;
	}
	
	public boolean isAutoApprove(String scope) {
		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BguClientDetails that = (BguClientDetails) o;
		return accessTokenValidity == that.accessTokenValidity &&
				refreshTokenValidity == that.refreshTokenValidity &&
				Objects.equals(id, that.id) &&
				Objects.equals(clientId, that.clientId) &&
				Objects.equals(registrationId, that.registrationId) &&
				Objects.equals(clientName, that.clientName) &&
				Objects.equals(clientSecret, that.clientSecret) &&
				Objects.equals(resourceIds, that.resourceIds) &&
				Objects.equals(scope, that.scope) &&
				Objects.equals(authorizedGrantTypes, that.authorizedGrantTypes) &&
				Objects.equals(registeredRedirectUri, that.registeredRedirectUri) &&
				Objects.equals(authorities, that.authorities) &&
				Objects.equals(additionalInformation, that.additionalInformation);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, clientId, registrationId, clientName, clientSecret, resourceIds, scope, authorizedGrantTypes, registeredRedirectUri, authorities, accessTokenValidity, refreshTokenValidity, additionalInformation);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", BguClientDetails.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("clientId='" + clientId + "'")
				.add("registrationId='" + registrationId + "'")
				.add("clientName='" + clientName + "'")
				.add("clientSecret='" + clientSecret + "'")
				.add("resourceIds='" + resourceIds + "'")
				.add("scope='" + scope + "'")
				.add("authorizedGrantTypes='" + authorizedGrantTypes + "'")
				.add("registeredRedirectUri='" + registeredRedirectUri + "'")
				.add("authorities='" + authorities + "'")
				.add("accessTokenValidity=" + accessTokenValidity)
				.add("refreshTokenValidity=" + refreshTokenValidity)
				.add("additionalInformation=" + additionalInformation)
				.toString();
	}
}
