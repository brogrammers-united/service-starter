package org.bgu.model.oauth.helper;

import org.bgu.model.oauth.BguClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;

public class BguClientRegistrationFactory {

	// Restrict instantiation
	private BguClientRegistrationFactory() {}
	
	/**
	 * 	Helper method to convert {@link BguClientRegistration} into an instance of {@link org.springframework.security.oauth2.client.registration.ClientRegistraiton}
	 */
	public static ClientRegistration getInstance(final BguClientRegistration appClient) {
		return ClientRegistration.withRegistrationId(appClient.getRegistrationId())
					.authorizationGrantType(appClient.getAuthorizationGrantType())
					.authorizationUri(appClient.getAuthorizationUri())
					.clientId(appClient.getClientId())
					.clientSecret(appClient.getClientSecret())
					.clientName(appClient.getClientName())
					.jwkSetUri(appClient.getJwkSetUri())
					.clientAuthenticationMethod(appClient.getClientAuthenticationMethod())
					.userInfoAuthenticationMethod(appClient.getUserInfoAuthenticationMethod())
					.providerConfigurationMetadata(appClient.getConfigurationMetadata())
					.redirectUriTemplate(appClient.getRedirectUriTemplate())
					.scope(appClient.getScope())
					.tokenUri(appClient.getTokenUri())
					.userInfoUri(appClient.getUserInfoUri())
					.userNameAttributeName(appClient.getUserNameAttributeName())
					.build();
	}
}
