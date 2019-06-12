package org.bgu.config.oauth;

import org.bgu.exception.ApplicationExceptionHandler;
import org.bgu.oauth.service.BguClientDetailsService;
import org.bgu.oauth.service.BguTokenEnhancer;
import org.bgu.security.KeyStoreService;
import org.bgu.security.KeyStoreServiceImpl;
import org.bgu.security.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@Configuration
@Import({ ApplicationExceptionHandler.class, KeyStoreServiceImpl.class, TokenServiceImpl.class })
public class OAuth2Beans {
	
	public static final String TOKEN_KEY = "Authorization";
	public static final String BEARER = "Bearer ";
	
	@Autowired
	private BguClientDetailsService clientDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(11);
	}

	@Bean
	public JwtTokenStore jwtTokenStore(final KeyStoreService keyStoreService) {
		return new JwtTokenStore(jwtAccessTokenConverter(keyStoreService));
	}

	@Bean("tokenEnhancerChain")
	public TokenEnhancer tokenEnhancer(final KeyStoreService keyStoreService) {
		TokenEnhancerChain chain = new TokenEnhancerChain();
		chain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter(keyStoreService), new BguTokenEnhancer()));
		return chain;
	}
	
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
