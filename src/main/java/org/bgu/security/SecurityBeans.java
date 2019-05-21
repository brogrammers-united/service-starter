package org.bgu.security;

import org.bgu.config.annotation.RequiresSecurityInclusion;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@RequiresSecurityInclusion
@Configuration
@Import({ BguPreAuthenticationProvider.class, BguTokenAuthenticationFilter.class, PreAuthenticatedUserDetailsService.class, HttpCookieOAuth2AuthorizationRequestRepository.class } )
public class SecurityBeans {

}
