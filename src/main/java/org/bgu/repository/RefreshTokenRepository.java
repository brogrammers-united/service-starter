package org.bgu.repository;

import java.util.Optional;

import org.bgu.model.oauth.BguRefreshToken;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RefreshTokenRepository extends MongoRepository<BguRefreshToken, ObjectId>{

	Optional<BguRefreshToken> findOptionalByTokenId(String tokenId);
	long deleteByTokenId(String tokenId);
}
