package org.bgu.model.oauth;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.bgu.model.ClientAuthority;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accessTokenValidity;
		result = prime * result + ((additionalInformation == null) ? 0 : additionalInformation.hashCode());
		result = prime * result + ((authorities == null) ? 0 : authorities.hashCode());
		result = prime * result + ((authorizedGrantTypes == null) ? 0 : authorizedGrantTypes.hashCode());
		result = prime * result + ((clientId == null) ? 0 : clientId.hashCode());
		result = prime * result + ((clientName == null) ? 0 : clientName.hashCode());
		result = prime * result + ((clientSecret == null) ? 0 : clientSecret.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + refreshTokenValidity;
		result = prime * result + ((registeredRedirectUri == null) ? 0 : registeredRedirectUri.hashCode());
		result = prime * result + ((registrationId == null) ? 0 : registrationId.hashCode());
		result = prime * result + ((resourceIds == null) ? 0 : resourceIds.hashCode());
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
		BguClientDetails other = (BguClientDetails) obj;
		if (accessTokenValidity != other.accessTokenValidity)
			return false;
		if (additionalInformation == null) {
			if (other.additionalInformation != null)
				return false;
		} else if (!additionalInformation.equals(other.additionalInformation))
			return false;
		if (authorities == null) {
			if (other.authorities != null)
				return false;
		} else if (!authorities.equals(other.authorities))
			return false;
		if (authorizedGrantTypes == null) {
			if (other.authorizedGrantTypes != null)
				return false;
		} else if (!authorizedGrantTypes.equals(other.authorizedGrantTypes))
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (refreshTokenValidity != other.refreshTokenValidity)
			return false;
		if (registeredRedirectUri == null) {
			if (other.registeredRedirectUri != null)
				return false;
		} else if (!registeredRedirectUri.equals(other.registeredRedirectUri))
			return false;
		if (registrationId == null) {
			if (other.registrationId != null)
				return false;
		} else if (!registrationId.equals(other.registrationId))
			return false;
		if (resourceIds == null) {
			if (other.resourceIds != null)
				return false;
		} else if (!resourceIds.equals(other.resourceIds))
			return false;
		if (scope == null) {
			if (other.scope != null)
				return false;
		} else if (!scope.equals(other.scope))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ApplicationClientDetails [id=");
		builder.append(id);
		builder.append(", clientId=");
		builder.append(clientId);
		builder.append(", registrationId=");
		builder.append(registrationId);
		builder.append(", clientName=");
		builder.append(clientName);
		builder.append(", resourceIds=");
		builder.append(resourceIds);
		builder.append(", scope=");
		builder.append(scope);
		builder.append(", authorizedGrantTypes=");
		builder.append(authorizedGrantTypes);
		builder.append(", registeredRedirectUri=");
		builder.append(registeredRedirectUri);
		builder.append(", authorities=");
		builder.append(authorities);
		builder.append(", accessTokenValidity=");
		builder.append(accessTokenValidity);
		builder.append(", refreshTokenValidity=");
		builder.append(refreshTokenValidity);
		builder.append(", additionalInformation=");
		builder.append(additionalInformation);
		builder.append("]");
		return builder.toString();
	}

}
