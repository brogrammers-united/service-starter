package org.bgu.config;

import org.bgu.config.annotation.ConditionalOnDownstreamService;
import org.bgu.oauth.service.interfaces.BguUserDetailsService;
import org.bgu.security.BguPreAuthenticationProvider;
import org.bgu.security.BguTokenAuthenticationFilter;
import org.bgu.security.HttpCookieOAuth2AuthorizationRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * @author William Gentry
 */
@ConditionalOnDownstreamService
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public WebSecurityConfig() {
        logger.info("Registering Downstream Service WebSecurityConfig!");
    }

    @Autowired
    private BguUserDetailsService userDetailsService;

    @Autowired
    private HttpCookieOAuth2AuthorizationRequestRepository requestRepo;

    @Autowired
    @Qualifier("bguPreAuthProvider")
    private BguPreAuthenticationProvider bguPreAuthProvider;

    @Autowired
    private BguTokenAuthenticationFilter tokenAuthenticationFilter;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(bguPreAuthProvider)
                .userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(bguPreAuthProvider)
                .csrf()
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .formLogin()
                    .disable()
                .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                .oauth2Login()
                    .authorizationEndpoint()
                    .authorizationRequestRepository(requestRepo)
                    .and()
                    .and()
                .addFilterAt(tokenAuthenticationFilter, OAuth2AuthorizationRequestRedirectFilter.class);
    }

}
