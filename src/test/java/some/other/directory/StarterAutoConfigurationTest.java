package some.other.directory;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.bgu.config.properties.KeyStoreProperties;
import org.bgu.config.properties.MailProperties;
import org.bgu.config.properties.MongoProperties;
import org.bgu.oauth.service.BguClientDetailsService;
import org.bgu.oauth.service.BguClientRegistrationRepository;
import org.bgu.oauth.service.BguTokenStore;
import org.bgu.oauth.service.interfaces.BguUserDetailsService;
import org.bgu.repository.AccessTokenRepository;
import org.bgu.repository.ApplicationUserRepository;
import org.bgu.repository.BguClientDetailsRepository;
import org.bgu.repository.RefreshTokenRepository;
import org.bgu.repository.impl.ApplicationUserRepositoryImpl;
import org.bgu.repository.impl.BguClientDetailsRepositoryImpl;
import org.bgu.security.ApplicationExceptionHandler;
import org.bgu.security.BguPreAuthenticationProvider;
import org.bgu.security.BguTokenAuthenticationFilter;
import org.bgu.security.PreAuthenticatedUserDetailsService;
import org.bgu.service.KeyStoreService;
import org.bgu.service.KeyStoreServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.test.context.junit4.SpringRunner;

import some.other.directory.config.WithAnnotationContextConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=WithAnnotationContextConfiguration.class)
public class StarterAutoConfigurationTest {

	@Autowired
	private ApplicationContext context;
	
	/**
	 * Purpose of these tests are to ensure that the beans associated with {@code TheAppStarter} will pull in all necessary beans
	 */
	
	@Test
	public void mongoTemplate_ShouldBeInApplicationContext() {
		assertNotNull("MongoTemplate could not be found in ApplicationContext", context.getBean(MongoTemplate.class));
	}
	
	
	/*
	 * Repositories
	 */
	@Test
	public void accessTokenRepository_ShouldBeInApplicationContext() {
		assertNotNull("AccessTokenRepository could not be found in ApplicationContext", context.getBean(AccessTokenRepository.class));
	}
	
	@Test
	public void refreshTokenRepository_ShouldBeInApplicationContext() {
		assertNotNull("RefreshTokenRepository could not be found in ApplicationContext", context.getBean(RefreshTokenRepository.class));
	}
	
	@Test
	public void applicationUserRepository_ShouldBeInApplicationContext() {
		assertTrue("ApplicationUserRepository could not be found in ApplicationContext", context.getBean(ApplicationUserRepository.class) instanceof ApplicationUserRepositoryImpl);
	}
	
	@Test
	public void clientDetailsRepository_ShouldBeInApplicationContext() {
		assertNotNull("BguClientDetailsRepository could not be found in ApplicationContext", context.getBean(BguClientDetailsRepository.class) instanceof BguClientDetailsRepositoryImpl);
	}
	
	/*
	 * Property Configuration Beans
	 */
	@Test
	public void keyStorePropertiesBean_ShouldBeInApplicationContext() {
		assertNotNull("KeyStoreProperties could not be found in ApplicationContext", context.getBean(KeyStoreProperties.class));
	}
	
	@Test
	public void mongoPropertiesBean_ShouldBeInApplicationContext() {
		assertNotNull("MongoProperties could not be found in ApplicationContext", context.getBean(MongoProperties.class));
	}
	
	@Test
	public void mailPropertiesBean_ShouldBeInApplicationContext() {
		assertNotNull("MailProperties could not be found in ApplicationContext", context.getBean(MailProperties.class));
	}
	
	/*
	 * Security Services
	 */
	@Test
	public void authenticationManager_ShouldBeInApplicationContext() {
		assertNotNull("AuthenticationManager could not be found in ApplicationContext", context.getBean(AuthenticationManager.class));
	}
	
	@Test
	public void bguUserDetailsService_ShouldBeInApplicationContext() {
		assertTrue("BguUserDetailsService could not be found in ApplicationContext", context.getBean(UserDetailsService.class) instanceof BguUserDetailsService);
	}
	
	@Test
	public void passwordEncoder_ShouldBeInApplicationContext() {
		assertTrue("PasswordEncoder could not be found in ApplicationContext", context.getBean(PasswordEncoder.class) instanceof BCryptPasswordEncoder);
	}
	
	@Test
	public void applicationExceptionHandler_ShouldBeInApplicationContext() {
		assertNotNull("ApplicationExceptionHandler could not be found in ApplicationContext", context.getBean(ApplicationExceptionHandler.class));
	}
	
	@Test
	public void keyStoreService_ShouldBeInApplicationContext() {
		assertTrue("KeyStoreService could not be found in ApplicationContext", context.getBean(KeyStoreService.class) instanceof KeyStoreServiceImpl);
	}
	
	@Test
	public void preAuthenticationUserDetailsService_ShouldBeInApplicationContext() {
		assertNotNull("PreAuthenticatedUserDetailsService could not be found in ApplicationContext", context.getBean(PreAuthenticatedUserDetailsService.class));
	}
	
	@Test
	public void bguPreAuthenticationProvider_ShouldBeInApplicationContext() {
		assertTrue("BguPreAuthenticationProvider could not be found in ApplicationContext", context.getBean("bguPreAuthProvider") instanceof BguPreAuthenticationProvider);
	}
	
	@Test
	public void bguTokenAuthenticationFilter_ShouldBeInApplicationContext() {
		assertNotNull("BguTokenAuthenticationFilter could not be found in ApplicationContext", context.getBean(BguTokenAuthenticationFilter.class));
	}
	
	/*
	 * OAuth2 Services
	 */
	
	@Test
	public void bguClientDetailsService_ShouldBeInApplicationContext() {
		assertTrue("BguClientDetailsService could not be found in ApplicationContext", context.getBean(ClientDetailsService.class) instanceof BguClientDetailsService);
	}
	
	@Test
	public void bguTokenStore_ShouldBeInApplicationContext() {
		assertTrue("BguTokenStore could not be found in ApplicationContext", context.getBean(TokenStore.class) instanceof BguTokenStore);
	}
	
	@Test
	public void bguTokenEnhancer_ShouldBeInApplicationContext() {
		assertTrue("BguTokenEnhancer could not be found in ApplicationContext", context.getBean(TokenEnhancer.class) instanceof TokenEnhancerChain);
	}
	
	@Test
	public void bguClientRegistrationRepository_ShouldBeInApplicationContext() {
		assertTrue("BguClientRegistrationRepository could not be found in ApplicationContext", context.getBean(ClientRegistrationRepository.class) instanceof BguClientRegistrationRepository);
	}
}
