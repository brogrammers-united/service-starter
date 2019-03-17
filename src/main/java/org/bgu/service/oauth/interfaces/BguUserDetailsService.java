package org.bgu.service.oauth.interfaces;

import org.bgu.exception.EmailNotFoundException;
import org.bgu.model.interfaces.BguUserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface BguUserDetailsService extends UserDetailsService {

	BguUserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	BguUserDetails loadUserByEmail(String email) throws EmailNotFoundException;
}
