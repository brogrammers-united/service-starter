package org.bgu.repository.impl;

import java.util.Optional;

import org.bgu.model.oauth.BguUser;
import org.bgu.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ApplicationUserRepositoryImpl implements ApplicationUserRepository {

	private final MongoTemplate template;
	
	@Autowired
	public ApplicationUserRepositoryImpl(final MongoTemplate template) {
		this.template = template;
	}

	@Override
	public Optional<BguUser> loadUserByUsername(String username) {
		return Optional.of(template.findOne(Query.query(Criteria.where("username").is(username)), BguUser.class, "bgu_user"));
	}

	@Override
	public Optional<BguUser> loadUserByEmail(String email) {
		return Optional.of(template.findOne(Query.query(Criteria.where("email").is(email)), BguUser.class, "bgu_user"));
	}
}
