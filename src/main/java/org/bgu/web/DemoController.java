package org.bgu.web;

import java.security.Principal;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableResourceServer
public class DemoController {

	@PreAuthorize("isAuthenticated() && #oauth2.hasScope('ROLE_API_ADMIN')")
	@GetMapping(value="/demo", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, String> testDownstreamService(Principal principal) {
		return Collections.singletonMap("message", "Hello from the starter service, " + principal.getName() + "! The time is " + LocalTime.now());
	}
}
