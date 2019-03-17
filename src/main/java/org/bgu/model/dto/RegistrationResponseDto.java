package org.bgu.model.dto;

public class RegistrationResponseDto {

	private final String message;
	
	public RegistrationResponseDto(final String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
