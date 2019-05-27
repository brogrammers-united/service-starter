package org.bgu.security;

import org.bgu.config.annotation.ConditionalOnDownstreamService;
import org.bgu.model.BguUser;
import org.bgu.model.interfaces.BguUserDetails;
import org.bgu.oauth.service.BguTokenStore;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * @author William Gentry
 */
@ConditionalOnDownstreamService
@Service
public class PreAuthenticatedUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    private final BguTokenStore tokenStore;

    public PreAuthenticatedUserDetailsService(BguTokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @Override
    public BguUserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        return (BguUser) tokenStore.readAuthentication(token.getName()).getPrincipal();
    }

}
