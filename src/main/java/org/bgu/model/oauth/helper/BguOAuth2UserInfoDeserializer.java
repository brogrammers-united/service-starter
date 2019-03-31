package org.bgu.model.oauth.helper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bgu.model.GithubBguOAuth2UserInfo;

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
public class BguOAuth2UserInfoDeserializer extends StdDeserializer<GithubBguOAuth2UserInfo> {

	private static final long serialVersionUID = 1L;

	public BguOAuth2UserInfoDeserializer() {
		this(null);
	}
	
	public BguOAuth2UserInfoDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public GithubBguOAuth2UserInfo deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		final JsonNode node = p.getCodec().readTree(p);
		Map<String, Object> attributes = new HashMap<>();
		attributes.put("id", node.get("id").asText());
		attributes.put("name", node.get("name").asText());
		attributes.put("login", node.get("login").asText());
		attributes.put("avatar_url", node.get("avatar_url").asText());
		attributes.put("email", node.get("email").asText());
		attributes.put("two_factor_authentication", node.get("two_factor_authentication").asText());
		return new GithubBguOAuth2UserInfo(attributes);
	}

}
