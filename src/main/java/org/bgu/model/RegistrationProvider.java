package org.bgu.model;

import java.util.Set;

public interface RegistrationProvider {

	String getRegistrationId();
	Set<String> getGrantType();
}
