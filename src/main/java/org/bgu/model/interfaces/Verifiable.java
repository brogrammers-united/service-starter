package org.bgu.model.interfaces;

import org.bgu.model.RegistrationProvider;

public interface Verifiable {

	String getUsername();
	String getEmail();
	RegistrationProvider getRegistrationProvider();
}
