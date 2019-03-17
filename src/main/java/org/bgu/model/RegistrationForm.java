package org.bgu.model;

import java.io.Serializable;

import javax.validation.Valid;

public class RegistrationForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final String username;
	private final String password;
	private final String passwordMatch;
	private final String email;
	
	@Valid
	private final Phone phone;
	
	@Valid
	private final PersonalInformation personalInformation;

	public RegistrationForm(String username, String password, String passwordMatch, String email, Phone phone, PersonalInformation personalInformation) {
		super();
		this.username = username;
		this.password = password;
		this.passwordMatch = passwordMatch;
		this.email = email;
		this.phone = phone;
		this.personalInformation = personalInformation;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getPasswordMatch() {
		return passwordMatch;
	}

	public String getEmail() {
		return email;
	}

	public Phone getPhone() {
		return phone;
	}

	public PersonalInformation getPersonalInformation() {
		return personalInformation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((passwordMatch == null) ? 0 : passwordMatch.hashCode());
		result = prime * result + ((personalInformation == null) ? 0 : personalInformation.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		RegistrationForm other = (RegistrationForm) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (passwordMatch == null) {
			if (other.passwordMatch != null)
				return false;
		} else if (!passwordMatch.equals(other.passwordMatch))
			return false;
		if (personalInformation == null) {
			if (other.personalInformation != null)
				return false;
		} else if (!personalInformation.equals(other.personalInformation))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RegistrationForm [username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", passwordMatch=");
		builder.append(passwordMatch);
		builder.append(", email=");
		builder.append(email);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", personalInformation=");
		builder.append(personalInformation);
		builder.append("]");
		return builder.toString();
	}

}
