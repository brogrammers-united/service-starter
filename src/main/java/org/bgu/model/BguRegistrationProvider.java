package org.bgu.model;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum BguRegistrationProvider implements RegistrationProvider {
	
	CLI("bgu-cli", "client_credentials"), WEB_APP("bgu-web-app", "authorization_code,password"), GITHUB("github", "authorization_code");
	
	private final String registrationId;
	private final String grantType;
	
	BguRegistrationProvider(final String registrationId, final String grantType) {
		this.registrationId = registrationId;
		this.grantType = grantType;
	}
	
	public String getRegistrationId() {
		return registrationId;
	}
	
	public Set<String> getGrantType() {
		return Arrays.stream(grantType.split(",")).collect(Collectors.toSet());
	}
}
