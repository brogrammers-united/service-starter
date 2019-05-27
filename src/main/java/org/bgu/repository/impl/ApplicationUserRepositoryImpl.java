package org.bgu.repository.impl;

import org.bgu.model.BguOAuth2UserInfo;
import org.bgu.model.BguUser;
import org.bgu.repository.ApplicationUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ApplicationUserRepositoryImpl implements ApplicationUserRepository {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final MongoTemplate oauthMongoTemplate;
	
	public ApplicationUserRepositoryImpl(@Qualifier("oauthMongoTemplate") MongoTemplate oauthMongoTemplate) {
		this.oauthMongoTemplate = oauthMongoTemplate;
	}

	@Override
	public Optional<BguUser> loadUserByUsername(String username) {
		return Optional.of(oauthMongoTemplate.findOne(Query.query(Criteria.where("username").is(username)), BguUser.class, "bgu_user"));
	}

	@Override
	public Optional<BguUser> loadUserByEmail(String email) {
		return Optional.of(oauthMongoTemplate.findOne(Query.query(Criteria.where("email").is(email)), BguUser.class, "bgu_user"));
	}

	@Override
	public Optional<BguUser> updateUser(String accessToken, BguOAuth2UserInfo info) {
		logger.trace("Attempting to update user: {}", info.getUsername());
		return oauthMongoTemplate.update(BguUser.class).matching(Query.query(Criteria.where("email").is(info.getEmail()))).apply(getUserUpdate(accessToken, info))
				.withOptions(new FindAndModifyOptions().returnNew(true))
				.findAndModify();
	}

	@Override
	public Optional<BguUser> attemptRegistration(BguUser user) {
		return Optional.ofNullable(oauthMongoTemplate.save(user, "bgu_user"));
	}

	private final Update getUserUpdate(String accessToken, BguOAuth2UserInfo info) {
		Update update = new Update();
		update.set("username", info.getUsername());
		update.set("email", info.getEmail());
		update.set("attributes", info.getAttributes());
		update.set("githubOAuthToken", accessToken);
		return update;
	}
}
