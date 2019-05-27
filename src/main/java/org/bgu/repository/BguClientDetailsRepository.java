package org.bgu.repository;

import org.bgu.model.oauth.BguClientDetails;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.Optional;

public interface BguClientDetailsRepository {

	ClientDetails save(ClientDetails details);
	Optional<BguClientDetails> loadClientDetailsByClientId(String clientId);
}
