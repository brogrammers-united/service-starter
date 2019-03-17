package org.bgu.repository;

import java.util.Optional;

import org.bgu.model.oauth.BguClientDetails;
import org.springframework.security.oauth2.provider.ClientDetails;

public interface BguClientDetailsRepository {

	ClientDetails save(ClientDetails details);
	Optional<BguClientDetails> loadClientDetailsByClientId(String clientId);
	Optional<BguClientDetails> loadClientDetailsByRegistrationId(String registrationId);
}
