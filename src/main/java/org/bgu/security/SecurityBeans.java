package org.bgu.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author William Gentry
 */
@Configuration
@Import({ HttpCookieOAuth2AuthorizationRequestRepository.class, BguPreAuthenticationProvider.class, BguTokenAuthenticationFilter.class, PreAuthenticatedUserDetailsService.class } )
public class SecurityBeans {
}
