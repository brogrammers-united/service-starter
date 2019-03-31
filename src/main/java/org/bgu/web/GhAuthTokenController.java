package org.bgu.web;

import java.util.Collections;
import java.util.Map;

import org.bgu.model.interfaces.BguUserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GhAuthTokenController {

	@GetMapping("/token")
	public Map<String, String> getGhAccessToken(OAuth2Authentication authentication) {
		BguUserDetails details = (BguUserDetails) authentication.getPrincipal();
		return Collections.singletonMap("access_token", details.getGithubOAuthToken());
	}
}
