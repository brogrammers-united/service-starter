package org.bgu.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ BguPreAuthenticationProvider.class, BguTokenAuthenticationFilter.class, PreAuthenticatedUserDetailsService.class } )
public class SecurityBeans {

}
