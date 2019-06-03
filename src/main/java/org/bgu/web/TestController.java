package org.bgu.web;

import org.bgu.config.annotation.ConditionalOnDownstreamService;
import org.bgu.config.annotation.CurrentUser;
import org.bgu.model.interfaces.BguUserDetails;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@ConditionalOnDownstreamService
@RestController
public class TestController {

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/test")
	public Map<String, String> getUsername(OAuth2Authentication authentication) {
		return Collections.singletonMap("username", authentication.getName());
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/test/annotation")
	public Map<String, String> getUsername(@CurrentUser BguUserDetails details) {
		return Collections.singletonMap("token", details.getGithubOAuthToken());
	}
}
