package org.bgu.oauth.service.interfaces;

import org.bgu.exception.EmailNotFoundException;
import org.bgu.model.BguOAuth2UserInfo;
import org.bgu.model.interfaces.BguUserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface BguUserDetailsService extends UserDetailsService {

	BguUserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	BguUserDetails loadUserByEmail(String email) throws EmailNotFoundException;
	BguUserDetails updateUserWithOAuth2Info(String accessToken, BguOAuth2UserInfo userInfo);
}
