package org.bgu.repository;

import java.util.List;
import java.util.Optional;

import org.bgu.model.oauth.BguAccessToken;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccessTokenRepository extends MongoRepository<BguAccessToken, ObjectId>{

	List<BguAccessToken> findByClientId(String clientId);
	List<BguAccessToken> findByClientIdAndUsername(String clientId, String username);
	Optional<BguAccessToken> findByTokenId(String tokenId);
	Optional<BguAccessToken> findByRefreshToken(String refreshToken);
	Optional<BguAccessToken> findByAuthenticationId(String authenticationId);
	long deleteByTokenId(String tokenId);
}
