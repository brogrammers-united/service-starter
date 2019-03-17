package org.bgu.model;

import org.springframework.security.core.GrantedAuthority;

public class UserAuthority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	
	private final String authority;

	public UserAuthority(final String authority) {
		this.authority = authority;
	}

	public String getAuthority() {
		return authority;
	}

}
