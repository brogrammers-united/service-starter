package org.bgu.repository.impl;

import java.util.Optional;

import org.bgu.model.oauth.BguClientDetails;
import org.bgu.repository.BguClientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.stereotype.Repository;

@Repository
public class BguClientDetailsRepositoryImpl implements BguClientDetailsRepository {

	private final MongoTemplate oauthMongoTemplate;
	
	@Autowired
	public BguClientDetailsRepositoryImpl(@Qualifier("oauthMongoTemplate") MongoTemplate oauthMongoTemplate) {
		this.oauthMongoTemplate = oauthMongoTemplate;
	}

	@Override
	public ClientDetails save(ClientDetails details) {
		return oauthMongoTemplate.save(details, "bgu_client_details");
	}
	
	@Override
	public Optional<BguClientDetails> loadClientDetailsByClientId(String clientId) {
		return Optional.of(oauthMongoTemplate.findOne(Query.query(Criteria.where("clientId").is(clientId)), BguClientDetails.class, "bgu_client_details"));
	}

	@Override
	public Optional<BguClientDetails> loadClientDetailsByRegistrationId(String registrationId) {
		return Optional.of(oauthMongoTemplate.findOne(Query.query(Criteria.where("registrationId").is(registrationId)), BguClientDetails.class, "bgu_client_details"));
	}
	
	
}
