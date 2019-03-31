package org.bgu.repository;

import java.util.List;
import java.util.Optional;

import org.bgu.model.oauth.BguAccessToken;

public interface AccessTokenRepository {

	List<BguAccessToken> findByClientId(String clientId);
	List<BguAccessToken> findByClientIdAndUsername(String clientId, String username);
	Optional<BguAccessToken> findByTokenId(String tokenId);
	Optional<BguAccessToken> findByRefreshToken(String refreshToken);
	Optional<BguAccessToken> findByAuthenticationId(String authenticationId);
	BguAccessToken save(BguAccessToken token);
	boolean delete(BguAccessToken token);
	long deleteByTokenId(String tokenId);
}
