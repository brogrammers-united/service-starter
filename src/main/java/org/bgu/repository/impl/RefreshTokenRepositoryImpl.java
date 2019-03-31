package org.bgu.repository.impl;

import java.util.Optional;

import org.bgu.model.oauth.BguAccessToken;
import org.bgu.model.oauth.BguRefreshToken;
import org.bgu.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

	private final MongoTemplate oauthMongoTemplate;
	
	public RefreshTokenRepositoryImpl(@Qualifier("oauthMongoTemplate") MongoTemplate oauthMongoTemplate) {
		this.oauthMongoTemplate = oauthMongoTemplate;
	}
	
	@Override
	public Optional<BguRefreshToken> findOptionalByTokenId(String tokenId) {
		return Optional.ofNullable(oauthMongoTemplate.findOne(Query.query(Criteria.where("tokenId").is(tokenId)), BguRefreshToken.class, "bgu_refresh_token"));
	}

	@Override
	public BguRefreshToken save(BguRefreshToken token) {
		return oauthMongoTemplate.save(token, "bgu_refresh_token");
	}

	@Override
	public boolean delete(BguRefreshToken token) {
		return oauthMongoTemplate.remove(token, "bgu_refresh_token").wasAcknowledged();
	}

	@Override
	public long deleteByTokenId(String tokenId) {
		return oauthMongoTemplate.remove(Query.query(Criteria.where("tokenId").is(tokenId)), BguAccessToken.class, "bgu_refresh_token").getDeletedCount();
	}

}
