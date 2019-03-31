package org.bgu.model.oauth;

import org.bgu.model.interfaces.BguUserDetails;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class BguOAuth2AuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 1L;
	
	private final BguUserDetails userDetails;

	public BguOAuth2AuthenticationToken(BguUserDetails userDetails) {
		super(userDetails.getAuthorities());
		this.userDetails = userDetails;
		super.setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return userDetails;
	}

}
