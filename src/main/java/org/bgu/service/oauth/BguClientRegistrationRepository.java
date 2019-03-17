package org.bgu.service.oauth;

import org.bgu.model.oauth.BguClientRegistration;
import org.bgu.model.oauth.helper.BguClientRegistrationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

@Service
public class BguClientRegistrationRepository implements ClientRegistrationRepository {

	private final MongoTemplate template;
	
	@Autowired
	public BguClientRegistrationRepository(MongoTemplate template) {
		this.template = template;
	}
	
	@Override
	public ClientRegistration findByRegistrationId(String registrationId) {
		final BguClientRegistration appClient = template.findOne(Query.query(Criteria.where("registrationId").is(registrationId)), BguClientRegistration.class, "bgu_client_registration");
		if (appClient == null)
			throw new ClientRegistrationException("Failed to locate client with specified id");
		return BguClientRegistrationFactory.getInstance(appClient);
	}

}
