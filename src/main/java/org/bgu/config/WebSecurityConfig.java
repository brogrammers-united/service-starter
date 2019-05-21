package org.bgu.config;

import org.bgu.config.annotation.RequiresSecurityInclusion;
import org.bgu.oauth.service.interfaces.BguUserDetailsService;
import org.bgu.security.BguPreAuthenticationProvider;
import org.bgu.security.HttpCookieOAuth2AuthorizationRequestRepository;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@RequiresSecurityInclusion
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BguUserDetailsService userDetailsService;
	
	@Autowired
	private HttpCookieOAuth2AuthorizationRequestRepository requestRepo;
	
	@Autowired
	@Qualifier("bguPreAuthProvider")
	private BguPreAuthenticationProvider bguPreAuthProvider;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

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
			.httpBasic()
				.disable()
			.formLogin()
				.disable()
			.authorizeRequests()
				.anyRequest().authenticated()
				.and()
			.oauth2Login()
				.authorizationEndpoint()
					.authorizationRequestRepository(requestRepo);
	}
	
}
