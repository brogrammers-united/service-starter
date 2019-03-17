package org.bgu.model.oauth.helper;

import java.io.IOException;

import org.bgu.model.dto.UserInfoRequest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * @author William
 *
 *	Jackson deserializer to pull necessary information from the GH user info response
 */
public class UserInfoRequestDeserializer extends StdDeserializer<UserInfoRequest> {
	
	private static final long serialVersionUID = 1L;

	public UserInfoRequestDeserializer() {
		this(null);
	}
	
	protected UserInfoRequestDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public UserInfoRequest deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		final JsonNode node = p.getCodec().readTree(p);
		final UserInfoRequest request = new UserInfoRequest();
		request.setEmail(node.get("email").asText());
		request.setId(node.get("id").asInt());
		request.setImageUrl(node.get("avatar_url").asText());
		request.setName(node.get("name").asText());
		request.setUsername(node.get("login").asText());
		return request;
	}

}
