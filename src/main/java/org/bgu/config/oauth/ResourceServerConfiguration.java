package org.bgu.config.oauth;

import org.bgu.oauth.service.BguClientDetailsService;
import org.bgu.oauth.service.BguTokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private BguTokenStore tokenStore;
	
	@Autowired
	private TokenEnhancer tokenEnhancerChain;
	
	@Autowired
	private BguClientDetailsService clientDetailsService;
	
	@Bean
	@Primary
	public ResourceServerTokenServices tokenServices() {
		DefaultTokenServices services = new DefaultTokenServices();
		services.setAuthenticationManager(authenticationManager);
		services.setTokenStore(tokenStore);
		services.setTokenEnhancer(tokenEnhancerChain);
		services.setSupportRefreshToken(true);
		services.setRefreshTokenValiditySeconds(60_000);
		services.setClientDetailsService(clientDetailsService);
		return services;
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources
			.authenticationManager(authenticationManager)
			.stateless(true)
			.tokenStore(tokenStore)
			.tokenServices(tokenServices());
	}
	
}
