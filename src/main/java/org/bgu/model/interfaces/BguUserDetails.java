package org.bgu.model.interfaces;

import org.bgu.model.RegistrationProvider;
import org.springframework.security.core.userdetails.UserDetails;

public interface BguUserDetails extends UserDetails {

	String getEmail();
	RegistrationProvider getRegistrationProvider();
}
