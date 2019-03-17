package org.bgu.model.oauth;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bgu_verification_token")
public class VerificationToken {

	@Id
	private ObjectId id;

	@Indexed(unique = true)
	private final String verification;

	private final String email;
	private final LocalDateTime timestamp;

	@PersistenceConstructor
	public VerificationToken(final String verification, final String email, final LocalDateTime timestamp) {
		this.verification = verification;
		this.email = email;
		this.timestamp = timestamp;
	}

	public String getVerification() {
		return verification;
	}

	public String getEmail() {
		return email;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
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
		VerificationToken other = (VerificationToken) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
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
		builder.append("VerificationToken [verification=");
		builder.append(verification);
		builder.append(", email=");
		builder.append(email);
		builder.append(", timestamp=");
		builder.append(timestamp);
		builder.append("]");
		return builder.toString();
	}

}
