package org.bgu.security;

import javax.annotation.PostConstruct;

import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.stereotype.Service;

@Service("bguPreAuthProvider")
public class BguPreAuthenticationProvider extends PreAuthenticatedAuthenticationProvider {

	private final PreAuthenticatedUserDetailsService userDetailsService;
	
	public BguPreAuthenticationProvider(PreAuthenticatedUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@PostConstruct
	public void init() {
		super.setPreAuthenticatedUserDetailsService(userDetailsService);
	}
}
