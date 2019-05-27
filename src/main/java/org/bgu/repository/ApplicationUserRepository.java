package org.bgu.repository;

import org.bgu.model.BguOAuth2UserInfo;
import org.bgu.model.BguUser;

import java.util.Optional;

public interface ApplicationUserRepository {

	Optional<BguUser> loadUserByUsername(String username);
	Optional<BguUser> loadUserByEmail(String email);
	Optional<BguUser> updateUser(String accessToken, BguOAuth2UserInfo info);
	Optional<BguUser> attemptRegistration(BguUser user);
}
