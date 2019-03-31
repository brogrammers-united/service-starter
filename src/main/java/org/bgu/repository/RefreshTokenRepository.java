package org.bgu.repository;

import java.util.Optional;

import org.bgu.model.oauth.BguRefreshToken;

public interface RefreshTokenRepository {

	Optional<BguRefreshToken> findOptionalByTokenId(String tokenId);
	BguRefreshToken save(BguRefreshToken token);
	boolean delete(BguRefreshToken token);
	long deleteByTokenId(String tokenId);
}
