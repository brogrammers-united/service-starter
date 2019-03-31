package org.bgu.repository.impl;

import java.util.List;
import java.util.Optional;

import org.bgu.model.oauth.BguAccessToken;
import org.bgu.repository.AccessTokenRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class AccessTokenRepositoryImpl implements AccessTokenRepository {

	private final MongoTemplate oauthMongoTemplate;
	
	public AccessTokenRepositoryImpl(@Qualifier("oauthMongoTemplate") MongoTemplate oauthMongoTemplate) {
		this.oauthMongoTemplate = oauthMongoTemplate;
	}
	
	@Override
	public List<BguAccessToken> findByClientId(String clientId) {
		return oauthMongoTemplate.find(Query.query(Criteria.where("clientId").is(clientId)), BguAccessToken.class);
	}

	@Override
	public List<BguAccessToken> findByClientIdAndUsername(String clientId, String username) {
		return oauthMongoTemplate.find(Query.query(Criteria.where("clientId").is(clientId).and("username").is(username)), BguAccessToken.class);
	}

	@Override
	public Optional<BguAccessToken> findByTokenId(String tokenId) {
		return Optional.ofNullable(oauthMongoTemplate.findOne(Query.query(Criteria.where("tokenId").is(tokenId)), BguAccessToken.class, "bgu_access_token"));
	}

	@Override
	public Optional<BguAccessToken> findByRefreshToken(String refreshToken) {
		return Optional.ofNullable(oauthMongoTemplate.findOne(Query.query(Criteria.where("refreshToken").is(refreshToken)), BguAccessToken.class, "bgu_access_token"));
	}

	@Override
	public Optional<BguAccessToken> findByAuthenticationId(String authenticationId) {
		return Optional.ofNullable(oauthMongoTemplate.findOne(Query.query(Criteria.where("authenticationId").is(authenticationId)), BguAccessToken.class, "bgu_access_token"));
	}

	@Override
	public BguAccessToken save(BguAccessToken token) {
		return oauthMongoTemplate.save(token, "bgu_access_token");
	}

	@Override
	public boolean delete(BguAccessToken token) {
		return oauthMongoTemplate.remove(token, "bgu_access_token").wasAcknowledged();
	}

	@Override
	public long deleteByTokenId(String tokenId) {
		return oauthMongoTemplate.remove(Query.query(Criteria.where("tokenId").is(tokenId)), BguAccessToken.class, "bgu_access_token").getDeletedCount();
	}

}
