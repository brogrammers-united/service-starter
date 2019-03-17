package org.bgu.config.oauth;

import org.bgu.service.KeyStoreService;
import org.bgu.service.oauth.BguClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuth2Beans {
	
	@Autowired
	private BguClientDetailsService clientDetailsService;

	@Bean
	public JwtTokenStore jwtTokenStore(final KeyStoreService keyStoreService) {
		return new JwtTokenStore(jwtAccessTokenConverter(keyStoreService));
	}
	
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter(final KeyStoreService keyStoreService) {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setKeyPair(keyStoreService.getKeyPair());
		return converter;
	}
	
	@Bean
	public OAuth2RequestFactory oauth2RequestFactory() {
		return new DefaultOAuth2RequestFactory(clientDetailsService);
	}
	
	@Bean
	public OAuth2ProtectedResourceDetails github() {
		AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
		details.setId("github");
		details.setAccessTokenUri("https://github.com/login/oauth/access_token");
		details.setGrantType("authorization_code");
		details.setClientId(System.getenv("GH_API_CLIENT_ID"));
		details.setClientSecret(System.getenv("GH_API_CLIENT_SECRET"));
		details.setUserAuthorizationUri("https://github.com/login/oauth/authorize");
		details.setClientAuthenticationScheme(AuthenticationScheme.form);
		details.setTokenName("oauth_token");
		details.setUseCurrentUri(false);
		details.setPreEstablishedRedirectUri("http://localhost:8080/");
		return details;
	}

}
