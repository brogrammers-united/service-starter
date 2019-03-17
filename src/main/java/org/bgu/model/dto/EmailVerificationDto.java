package org.bgu.model.dto;

public class EmailVerificationDto {

	private final String verification;
	private final String email;

	public EmailVerificationDto(final String verification, final String email) {
		this.verification = verification;
		this.email = email;
	}

	public String getVerification() {
		return verification;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((verification == null) ? 0 : verification.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailVerificationDto other = (EmailVerificationDto) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (verification == null) {
			if (other.verification != null)
				return false;
		} else if (!verification.equals(other.verification))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmailVerificationDto [verification=");
		builder.append(verification);
		builder.append(", email=");
		builder.append(email);
		builder.append("]");
		return builder.toString();
	}

}
