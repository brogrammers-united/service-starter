package org.bgu.model.interfaces;

import org.bgu.model.oauth.helper.LoginPreference;
import org.springframework.security.core.userdetails.UserDetails;

public interface BguUserDetails extends UserDetails {

	int getUserId();
	String getEmail();
	String getName();
	String getGithubOAuthToken();
	LoginPreference getLoginPreference();
}
