package org.bgu.oauth.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bgu.exception.EmailNotFoundException;
import org.bgu.model.BguOAuth2UserInfo;
import org.bgu.model.BguUser;
import org.bgu.model.interfaces.BguUserDetails;
import org.bgu.oauth.service.interfaces.BguUserDetailsService;
import org.bgu.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class BguUserDetailsServiceImpl implements BguUserDetailsService {
	
	private final Logger logger = LogManager.getLogger(getClass());
	private final ApplicationUserRepository repo;
	
	@Autowired
	public BguUserDetailsServiceImpl(final ApplicationUserRepository repo) {
		this.repo = repo;
	}

	@Override
	public BguUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (!StringUtils.hasText(username))
			throw new UsernameNotFoundException("Must provide valid username");
		logger.debug("Attempting to authenticate user by username: {}", username);
		return repo.loadUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Invalid Credentials"));
	}

	@Override
	public BguUserDetails loadUserByEmail(String email) throws EmailNotFoundException {
		if (!StringUtils.hasText(email))
			throw new EmailNotFoundException("Must provide valid email");
		logger.debug("Attempting to authenticate user by email: {}", email);
		return repo.loadUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Invalid Credentials"));
	}

	@Override
	public BguUserDetails updateUserWithOAuth2Info(String accessToken, BguOAuth2UserInfo userInfo) {
		logger.debug("Attempting to update: {}", userInfo.getUsername());
		BguUser user =  repo.updateUser(accessToken, userInfo).orElseThrow(RuntimeException::new);
		return user;
	}

}
