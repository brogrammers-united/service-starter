package org.bgu.oauth.service;

import org.bgu.model.oauth.BguClientDetails;
import org.bgu.repository.BguClientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

@Service
public class BguClientDetailsService implements ClientDetailsService {

	private final BguClientDetailsRepository repo;
	
	@Autowired
	public BguClientDetailsService(final BguClientDetailsRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public BguClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		return repo.loadClientDetailsByClientId(clientId).orElseThrow(() -> new ClientRegistrationException("Failed to load client with id [" + clientId + "]"));
	}

}
