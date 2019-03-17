package org.bgu.service.oauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ BguClientDetailsService.class, BguClientRegistrationRepository.class, BguTokenStore.class, BguUserDetailsServiceImpl.class})
public class ServiceConfiguration {

}
