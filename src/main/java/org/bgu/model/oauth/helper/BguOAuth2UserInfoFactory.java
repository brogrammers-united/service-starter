package org.bgu.model.oauth.helper;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bgu.exception.InvalidClientRegistrationException;
import org.bgu.model.BguOAuth2UserInfo;
import org.bgu.model.BguRegistrationProvider;
import org.bgu.model.GithubBguOAuth2UserInfo;

public class BguOAuth2UserInfoFactory {

	private static final Logger logger = LogManager.getLogger(BguOAuth2UserInfoFactory.class);
	
	// Restrict instantiation
	private BguOAuth2UserInfoFactory() {}
	
	public static BguOAuth2UserInfo getUserInfo(String registrationId, Map<String, Object> attributes) {
		logger.info("Attempting to generate BguOAuth2UserInfo for registrationId: {}", registrationId);
		if (registrationId.equals(BguRegistrationProvider.GITHUB.getRegistrationId()))
			return new GithubBguOAuth2UserInfo(attributes);
		throw new InvalidClientRegistrationException();
	}
}
