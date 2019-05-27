package org.bgu.oauth.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bgu.model.oauth.BguAccessToken;
import org.bgu.repository.AccessTokenRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.security.oauth2.common.util.SerializationUtils.deserialize;
import static org.springframework.security.oauth2.common.util.SerializationUtils.serialize;

@Primary
@Service
public class BguTokenStore implements TokenStore {

	private final AccessTokenRepository accessTokenRepo;

	private final Logger logger = LogManager.getLogger(getClass());
	private final AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();

	public BguTokenStore(AccessTokenRepository accessTokenRepo) {
		this.accessTokenRepo = accessTokenRepo;
	}

	@Override
	public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
		return readAuthentication(token.getValue());
	}

	@Override
	public OAuth2Authentication readAuthentication(String token) {
		Optional<BguAccessToken> accessToken = accessTokenRepo.findByTokenId(extractTokenKey(token));
		if (accessToken.isPresent()) {
			try {
				return deserializeAuthentication(accessToken.get().getAuthentication());
			} catch (IllegalArgumentException e) {
				removeAccessToken(token);
			}
		}
		return null;
	}

	@Override
	public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
		logger.info("Attempting to save incoming OAuth2AccessToken ID: {}", extractTokenKey(token.getValue()));
		logger.info("Value of OAuth2Authentication: {}", authentication);

		final String authenticationId = authenticationKeyGenerator.extractKey(authentication);

		BguAccessToken example = new BguAccessToken();
		example.setAuthenticationId(authenticationId);

		if (accessTokenRepo.exists(Example.of(example))) {
			long result = accessTokenRepo.deleteAllByAuthenticationId(authenticationId);
			logger.info("{} records removed", result);
		}

		if (readAccessToken(token.getValue()) != null)
			removeAccessToken(token.getValue());

		final String tokenKey = extractTokenKey(token.getValue());

		BguAccessToken accessToken = new BguAccessToken(tokenKey,
											authenticationKeyGenerator.extractKey(authentication),
											authentication.isClientOnly() ? null : authentication.getName(),
											authentication.getOAuth2Request().getClientId(),
											serializeAuthentication(authentication),
											serializeAccessToken(token));

		
		logger.info("Saving access token {}", accessToken);
		accessTokenRepo.save(accessToken);
	}

	@Override
	public OAuth2AccessToken readAccessToken(String tokenValue) {
		logger.info("Attempting to read access token by ID: {}", extractTokenKey(tokenValue));
		Optional<BguAccessToken> accessToken = accessTokenRepo.findByTokenId(extractTokenKey(tokenValue));
		logger.info("Is the token present? {}", accessToken.isPresent());
		if (accessToken.isPresent())
			try {
				return deserializeAccessToken(accessToken.get().getToken());
			} catch (IllegalArgumentException e) {
				removeAccessToken(tokenValue);
			}
		return null;
	}

	@Override
	public void removeAccessToken(OAuth2AccessToken token) {
		logger.info("Attempting to remove OAuth2AccessToken by ID: {}", extractTokenKey(token.getValue()));
		Optional<BguAccessToken> accessToken = accessTokenRepo.findByTokenId(extractTokenKey(token.getValue()));
		if (accessToken.isPresent()) {
			accessTokenRepo.delete(accessToken.get());
			logger.info("Access token removed!");
		}
	}

	@Override
	public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
		throw new UnsupportedOperationException("Application does not support use of Refresh Tokens");
	}

	@Override
	public OAuth2RefreshToken readRefreshToken(String tokenValue) {
		throw new UnsupportedOperationException("Application does not support use of Refresh Tokens");
	}

	@Override
	public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
		throw new UnsupportedOperationException("Application does not support use of Refresh Tokens");
	}

	@Override
	public void removeRefreshToken(OAuth2RefreshToken token) {
		throw new UnsupportedOperationException("Application does not support use of Refresh Tokens");
	}

	@Override
	public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
		throw new UnsupportedOperationException("Application does not support use of Refresh Tokens");
	}

	@Override
	public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
		OAuth2AccessToken token = null;
		String authenticationId = authenticationKeyGenerator.extractKey(authentication);
		Optional<BguAccessToken> accessToken = accessTokenRepo.findByAuthenticationId(authenticationId);

		if (accessToken.isPresent())
			token = deserializeAccessToken(accessToken.get().getToken());

		if (token != null && !authenticationId.equals(authenticationKeyGenerator.extractKey(readAuthentication(token)))) {
			removeAccessToken(token);
			storeAccessToken(token, authentication);
		}
		return token;
	}

	@Override
	public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
		return accessTokenRepo.findByClientIdAndUsername(clientId, userName).stream().map(token -> deserializeAccessToken(token.getToken())).collect(Collectors.toList());
	}

	@Override
	public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
		return accessTokenRepo.findByClientId(clientId).stream().map(token -> deserializeAccessToken(token.getToken())).collect(Collectors.toList());
	}
	
	  private String extractTokenKey(String value) {
	        if(value == null) {
	            return null;
	        } else {
	            MessageDigest digest;
	            try {
	                digest = MessageDigest.getInstance("MD5");
	            } catch (NoSuchAlgorithmException var5) {
	                throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
	            }
	 
	            try {
	                byte[] e = digest.digest(value.getBytes("UTF-8"));
	                return String.format("%032x", new Object[]{new BigInteger(1, e)});
			} catch (UnsupportedEncodingException var4) {
				throw new IllegalStateException("UTF-8 encoding not available.  Fatal (should be in the JDK).");
			}
		}
	}

	protected byte[] serializeAccessToken(final OAuth2AccessToken token) {
		return serialize(token);
	}

	protected byte[] serializeAuthentication(final OAuth2Authentication authentication) {
		return serialize(authentication);
	}

	protected OAuth2AccessToken deserializeAccessToken(final byte[] token) {
		return deserialize(token);
	}

	protected OAuth2Authentication deserializeAuthentication(final byte[] authentication) {
		return deserialize(authentication);
	}

	private void removeAccessToken(final String tokenValue) {
		final String tokenKey = extractTokenKey(tokenValue);
		accessTokenRepo.deleteByTokenId(tokenKey);
	}
}
