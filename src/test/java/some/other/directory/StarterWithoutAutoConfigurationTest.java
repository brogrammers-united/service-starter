package some.other.directory;

import org.bgu.config.properties.KeyStoreProperties;
import org.bgu.config.properties.MailProperties;
import org.bgu.config.properties.MongoProperties;
import org.bgu.oauth.service.BguClientDetailsService;
import org.bgu.oauth.service.BguClientRegistrationRepository;
import org.bgu.oauth.service.interfaces.BguUserDetailsService;
import org.bgu.repository.AccessTokenRepository;
import org.bgu.repository.ApplicationUserRepository;
import org.bgu.repository.BguClientDetailsRepository;
import org.bgu.repository.RefreshTokenRepository;
import org.bgu.security.ApplicationExceptionHandler;
import org.bgu.security.BguPreAuthenticationProvider;
import org.bgu.security.BguTokenAuthenticationFilter;
import org.bgu.security.PreAuthenticatedUserDetailsService;
import org.bgu.service.KeyStoreServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootConfiguration
public class StarterWithoutAutoConfigurationTest {

	@Autowired
	private ApplicationContext context;
	
	/**
	 * Purpose of these tests are to ensure that the beans associated with {@code TheAppStarter} will not be pulled into the Application Context
	 */
	
	/*
	 * Repositories
	 */
	@Test(expected=NoSuchBeanDefinitionException.class)
	public void accessTokenRepository_ShouldNotBeFoundIn_ApplicationContext() {
		context.getBean(AccessTokenRepository.class);
	}
	
	@Test(expected=NoSuchBeanDefinitionException.class)
	public void refreshTokenRepository_ShouldNotBeFoundIn_ApplicationContext() {
		context.getBean(RefreshTokenRepository.class);
	}
	
	@Test(expected=NoSuchBeanDefinitionException.class)
	public void applicationUserRepository_ShouldNotBeFoundIn_ApplicationContext() {
		context.getBean(ApplicationUserRepository.class);
	}
	
	@Test(expected=NoSuchBeanDefinitionException.class)
	public void clientDetailsRepository_ShouldNotBeFoundIn_ApplicationContext() {
		context.getBean(BguClientDetailsRepository.class);
	}
	
	/*
	 * Property Configuration Beans
	 */
	@Test(expected=NoSuchBeanDefinitionException.class)
	public void keyStoreProperties_ShouldNotBeFoundIn_ApplicationContext() {
		context.getBean(KeyStoreProperties.class);
	}
	
	@Test(expected=NoSuchBeanDefinitionException.class)
	public void mongoProperties_ShouldNotBeFoundIn_ApplicationContext() {
		context.getBean(MongoProperties.class);
	}
	
	@Test(expected=NoSuchBeanDefinitionException.class)
	public void mailProperties_ShouldNotBeFoundIn_ApplicationContext() {
		context.getBean(MailProperties.class);
	}
	
	/*
	 * Security Services
	 */
	
	@Test(expected=NoSuchBeanDefinitionException.class)
	public void passwordEncoder_ShouldNotBeFoundIn_ApplicationContext() {
		context.getBean(PasswordEncoder.class);
	}
	
	@Test(expected=NoSuchBeanDefinitionException.class)
	public void bguUserDetailsService_ShouldNotBeFoundIn_ApplicationContext() {
		context.getBean(BguUserDetailsService.class);
	}
	
	@Test(expected=NoSuchBeanDefinitionException.class)
	public void keyStoreService_ShouldNotBeFoundIn_ApplicationContext() {
		context.getBean(KeyStoreServiceImpl.class);
	}
	
	@Test(expected=NoSuchBeanDefinitionException.class)
	public void applicationExceptionHandler_ShouldNotBeFoundIn_ApplicationContext() {
		context.getBean(ApplicationExceptionHandler.class);
	}
	
	@Test(expected=NoSuchBeanDefinitionException.class)
	public void bguPreAuthenticationProvider_ShouldNotBeFoundIn_ApplicationContext() {
		context.getBean(BguPreAuthenticationProvider.class);
	}
	
	@Test(expected=NoSuchBeanDefinitionException.class)
	public void preAuthenticatedUserDetailsService_ShouldNotBeIn_ApplicationContext() {
		context.getBean(PreAuthenticatedUserDetailsService.class);
	}
	
	@Test(expected=NoSuchBeanDefinitionException.class)
	public void bguTokenAuthenticationFilter_ShouldNotBeIn_ApplicationContext() {
		context.getBean(BguTokenAuthenticationFilter.class);
	}
	
	/*
	 * OAuth2 Services
	 */
	@Test(expected=NoSuchBeanDefinitionException.class)
	public void clientDetailsService_ShouldNotBeFoundIn_ApplicationContext() {
		context.getBean(BguClientDetailsService.class);
	}
	
	@Test(expected=NoSuchBeanDefinitionException.class)
	public void clientRegistrationRepository_ShouldNotBeFoundIn_ApplicationContext() {
		context.getBean(BguClientRegistrationRepository.class);
	}
	
	@Test(expected=NoSuchBeanDefinitionException.class)
	public void tokenStore_ShouldNotBeFoundIn_ApplicationContext() {
		context.getBean(TokenStore.class);
	}
	
	@Test(expected=NoSuchBeanDefinitionException.class)
	public void tokenEnhancer_ShouldNotBeFoundIn_ApplicationContext() {
		context.getBean(TokenEnhancer.class);
	}
}
