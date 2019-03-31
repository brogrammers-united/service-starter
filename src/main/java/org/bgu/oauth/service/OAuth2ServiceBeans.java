package org.bgu.oauth.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ BguClientDetailsService.class, BguClientRegistrationRepository.class, BguTokenStore.class, BguUserDetailsServiceImpl.class})
public class OAuth2ServiceBeans {

}
