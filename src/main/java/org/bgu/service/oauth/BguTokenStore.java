package org.bgu.service.oauth;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bgu.model.oauth.BguAccessToken;
import org.bgu.model.oauth.BguRefreshToken;
import org.bgu.repository.AccessTokenRepository;
import org.bgu.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

@Primary
@Service
public class BguTokenStore implements TokenStore {

	private final AccessTokenRepository accessTokenRepo;
	private final RefreshTokenRepository refreshTokenRepo;
	
	private final AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();
	
	@Autowired
	public BguTokenStore(AccessTokenRepository accessTokenRepo, RefreshTokenRepository refreshTokenRepo) {
		super();
		this.accessTokenRepo = accessTokenRepo;
		this.refreshTokenRepo = refreshTokenRepo;
	}

	@Override
	public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
		return readAuthentication(token.getValue());
	}

	@Override
	public OAuth2Authentication readAuthentication(String token) {
		Optional<BguAccessToken> accessToken = accessTokenRepo.findByTokenId(extractTokenKey(token));
		if (accessToken.isPresent())
			return accessToken.get().getAuthentication();
		return null;
	}

	@Override
	public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
		String refreshToken = null;
		if (token.getRefreshToken() != null)
			refreshToken = token.getRefreshToken().getValue();
		
		if (readAccessToken(token.getValue()) != null)
			this.removeAccessToken(token);
		
		BguAccessToken accessToken = new BguAccessToken();
		accessToken.setTokenId(extractTokenKey(token.getValue()));
		accessToken.setToken(token);
		accessToken.setAuthenticationId(authenticationKeyGenerator.extractKey(authentication));
		accessToken.setUsername(authentication.isClientOnly() ? null : authentication.getName());
		accessToken.setClientId(authentication.getOAuth2Request().getClientId());
		accessToken.setAuthentication(authentication);
		accessToken.setRefreshToken(extractTokenKey(refreshToken));
		
		accessTokenRepo.save(accessToken);
	}

	@Override
	public OAuth2AccessToken readAccessToken(String tokenValue) {
		Optional<BguAccessToken> accessToken = accessTokenRepo.findByTokenId(extractTokenKey(tokenValue));
		if (accessToken.isPresent())
			return accessToken.get().getToken();
		return null;
	}

	@Override
	public void removeAccessToken(OAuth2AccessToken token) {
		Optional<BguAccessToken> accessToken = accessTokenRepo.findByTokenId(extractTokenKey(token.getValue()));
		if (accessToken.isPresent())
			accessTokenRepo.delete(accessToken.get());
	}

	@Override
	public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
		BguRefreshToken token = new BguRefreshToken();
		token.setTokenId(extractTokenKey(refreshToken.getValue()));
		token.setToken(refreshToken);
		token.setAuthentication(authentication);
		refreshTokenRepo.save(token);
	}

	@Override
	public OAuth2RefreshToken readRefreshToken(String tokenValue) {
		Optional<BguRefreshToken> token = refreshTokenRepo.findOptionalByTokenId(extractTokenKey(tokenValue));
		return token.isPresent() ? token.get().getToken() : null;
	}

	@Override
	public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
		Optional<BguRefreshToken> refreshToken = refreshTokenRepo.findOptionalByTokenId(extractTokenKey(token.getValue()));
		return refreshToken.isPresent() ? refreshToken.get().getAuthentication() : null;
	}

	@Override
	public void removeRefreshToken(OAuth2RefreshToken token) {
		Optional<BguRefreshToken> refreshToken = refreshTokenRepo.findOptionalByTokenId(extractTokenKey(token.getValue()));
		if (refreshToken.isPresent())
			refreshTokenRepo.delete(refreshToken.get());
	}

	@Override
	public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
		Optional<BguAccessToken> accessToken = accessTokenRepo.findByRefreshToken(extractTokenKey(refreshToken.getValue()));
		if (accessToken.isPresent())
			accessTokenRepo.delete(accessToken.get());
	}

	@Override
	public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
		OAuth2AccessToken token = null;
		String authenticationId = authenticationKeyGenerator.extractKey(authentication);
		Optional<BguAccessToken> accessToken = accessTokenRepo.findByAuthenticationId(authenticationId);
		
		if (accessToken.isPresent()) {
			token = accessToken.get().getToken();
			if (token != null && !authenticationId.equals(this.authenticationKeyGenerator.extractKey(this.readAuthentication(token)))) {
				this.removeAccessToken(token);
				this.storeAccessToken(token, authentication);
			}
		}
		return token;
	}

	@Override
	public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
		return accessTokenRepo.findByClientIdAndUsername(clientId, userName).stream().map(token -> token.getToken()).collect(Collectors.toList());
	}

	@Override
	public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
		return accessTokenRepo.findByClientId(clientId).stream().map(token -> token.getToken()).collect(Collectors.toList());
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
}
