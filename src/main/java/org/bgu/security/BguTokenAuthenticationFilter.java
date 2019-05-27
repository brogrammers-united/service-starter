package org.bgu.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bgu.config.annotation.ConditionalOnDownstreamService;
import org.bgu.oauth.service.BguTokenStore;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author William Gentry
 */
@ConditionalOnDownstreamService
@Service
public class BguTokenAuthenticationFilter extends OncePerRequestFilter {

    private final Logger logger = LogManager.getLogger(getClass());
    private final BguTokenStore tokenStore;

    public BguTokenAuthenticationFilter(BguTokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("{} request going to {}", request.getMethod(), request.getRequestURI());
        final String token = CookieUtils.getCookie(request, "api_token").get().getValue();
        if (StringUtils.hasText(token)) {
            final OAuth2Authentication authentication = tokenStore.readAuthentication(token);
            logger.info("{} attempting to access {} at {}", authentication.getName(), request.getRequestURI(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy")));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.info( "Security context set!");
        }
        filterChain.doFilter(request, response);
    }
}
