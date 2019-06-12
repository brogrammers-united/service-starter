package org.bgu.repository;

import org.bgu.model.oauth.BguAccessToken;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AccessTokenRepository extends MongoRepository<BguAccessToken, ObjectId>{

	List<BguAccessToken> findByClientId(String clientId);
	List<BguAccessToken> findByClientIdAndUsername(String clientId, String username);
	Optional<BguAccessToken> findByTokenId(String tokenId);
	Optional<BguAccessToken> deleteByTokenId(String tokenId);
	Optional<BguAccessToken> findByAuthenticationId(String authenticationId);
	long deleteAllByAuthenticationId(String authenticationId);
}
