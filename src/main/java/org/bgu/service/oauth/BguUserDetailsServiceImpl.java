package org.bgu.service.oauth;

import org.bgu.exception.EmailNotFoundException;
import org.bgu.model.interfaces.BguUserDetails;
import org.bgu.repository.ApplicationUserRepository;
import org.bgu.service.oauth.interfaces.BguUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class BguUserDetailsServiceImpl implements BguUserDetailsService {
	
	private final ApplicationUserRepository repo;
	
	@Autowired
	public BguUserDetailsServiceImpl(final ApplicationUserRepository repo) {
		this.repo = repo;
	}

	@Override
	public BguUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (!StringUtils.hasText(username))
			throw new UsernameNotFoundException("Must provide valid username");
		return repo.loadUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Invalid Credentials"));
	}

	@Override
	public BguUserDetails loadUserByEmail(String email) throws EmailNotFoundException {
		if (!StringUtils.hasText(email))
			throw new EmailNotFoundException("Must provide valid email");
		return repo.loadUserByEmail(email).orElseThrow(() -> new EmailNotFoundException("Invalid Credentials"));
	}

}
