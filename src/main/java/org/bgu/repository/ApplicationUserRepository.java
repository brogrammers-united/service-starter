package org.bgu.repository;

import java.util.Optional;

import org.bgu.model.oauth.BguUser;

public interface ApplicationUserRepository {

	Optional<BguUser> loadUserByUsername(String username);
	Optional<BguUser> loadUserByEmail(String email);
}
