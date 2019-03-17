package org.bgu.service.oauth;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bgu.model.oauth.BguUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Service;

@Service
public class BguTokenEnhancer implements TokenEnhancer {
	
	private final Logger logger = LogManager.getLogger(getClass());

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		if (authentication.getOAuth2Request().getGrantType().equalsIgnoreCase("password")) {
			BguUser user = (BguUser) authentication.getPrincipal();
			final Map<String, Object> additionalInformation = new HashMap<>();

			additionalInformation.put("sub", user.getUsername());
			additionalInformation.put("email", user.getEmail());
			additionalInformation.put("authorities", user.getAuthorities());
			additionalInformation.put("name", user.getName());
			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);

			return accessToken;
		} else if (authentication.getOAuth2Request().getGrantType().equalsIgnoreCase("client_credentials")) {
			logger.info("Access Token: {}", accessToken);
			logger.info("Authentication: {}", authentication);
		}
		return accessToken;
	}

}
