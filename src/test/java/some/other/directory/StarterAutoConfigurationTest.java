package some.other.directory;

import org.bgu.config.properties.ApplicationMongoProperties;
import org.bgu.config.properties.KeyStoreProperties;
import org.bgu.config.properties.MongoProperties;
import org.bgu.oauth.service.BguClientDetailsService;
import org.bgu.oauth.service.BguClientRegistrationRepository;
import org.bgu.oauth.service.BguTokenStore;
import org.bgu.oauth.service.interfaces.BguUserDetailsService;
import org.bgu.repository.AccessTokenRepository;
import org.bgu.repository.ApplicationUserRepository;
import org.bgu.repository.BguClientDetailsRepository;
import org.bgu.repository.RefreshTokenRepository;
import org.bgu.security.ApplicationExceptionHandler;
import org.bgu.security.BguPreAuthenticationProvider;
import org.bgu.security.BguTokenAuthenticationFilter;
import org.bgu.security.PreAuthenticatedUserDetailsService;
import org.bgu.service.KeyStoreService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import some.other.directory.config.regular.WithAnnotationContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=WithAnnotationContextConfiguration.class)
public class StarterAutoConfigurationTest {

	@Autowired
	private ApplicationContext context;

	private ApplicationContextRunner runner;

	@Before
	public void setUpRunner() {
		runner = new ApplicationContextRunner().withParent(context);
	}
	
	/**
	 * Purpose of these tests are to ensure that the beans associated with {@code TheAppStarter} will pull in all necessary beans
	 */
	
	@Test
	public void mongoTemplate_ShouldBeInApplicationContext() {
		runner.run(context -> assertThat(context).getBean("oauthMongoTemplate").isNotNull());
		runner.run(context -> assertThat(context).getBean("applicationMongoTemplate").isNotNull());
	}
	
	
	/*
	 * Repositories
	 */
	@Test
	public void accessTokenRepository_ShouldBeInApplicationContext() {
		runner.run(context -> {
			assertThat(context).hasSingleBean(AccessTokenRepository.class);
		});
	}
	
	@Test
	public void refreshTokenRepository_ShouldBeInApplicationContext() {
		runner.run(context -> {
			assertThat(context).hasSingleBean(RefreshTokenRepository.class);
		});
	}
	
	@Test
	public void applicationUserRepository_ShouldBeInApplicationContext() {
		runner.run(context -> {
			assertThat(context).hasSingleBean(ApplicationUserRepository.class);
		});
	}
	
	@Test
	public void clientDetailsRepository_ShouldBeInApplicationContext() {
		runner.run(context -> {
			assertThat(context).hasSingleBean(BguClientDetailsRepository.class);
		});
	}
	
	/*
	 * Property Configuration Beans
	 */
	@Test
	public void keyStorePropertiesBean_ShouldBeInApplicationContext() {
		runner.run(context -> {
			assertThat(context).hasSingleBean(KeyStoreProperties.class);
		});
	}
	
	@Test
	public void mongoPropertiesBean_ShouldBeInApplicationContext() {
		runner.run(context -> {
			assertThat(context).hasSingleBean(MongoProperties.class);
		});
	}

	@Test
	public void applicationMongoPropertiesBean_ShouldBeInApplicationContext() {
		runner.run(context -> {
			assertThat(context).hasSingleBean(ApplicationMongoProperties.class);
		});
	}
	
	/*
	 * Security Services
	 */
	@Test
	public void authenticationManager_ShouldBeInApplicationContext() {
		runner.run(context -> {
			assertThat(context).hasSingleBean(AuthenticationManager.class);
		});
	}
	
	@Test
	public void bguUserDetailsService_ShouldBeInApplicationContext() {
		runner.run(context -> {
			assertThat(context).hasSingleBean(BguUserDetailsService.class);
		});
	}
	
	@Test
	public void passwordEncoder_ShouldBeInApplicationContext() {
		runner.run(context -> {
			assertThat(context).hasSingleBean(PasswordEncoder.class);
		});
	}
	
	@Test
	public void applicationExceptionHandler_ShouldBeInApplicationContext() {
		runner.run(context -> {
			assertThat(context).hasSingleBean(ApplicationExceptionHandler.class);
		});
	}
	
	@Test
	public void keyStoreService_ShouldBeInApplicationContext() {
		runner.run(context -> {
			assertThat(context).hasSingleBean(KeyStoreService.class);
		});
	}
	
	@Test
	public void preAuthenticationUserDetailsService_ShouldBeInApplicationContext() {
		runner.run(context -> {
			assertThat(context).hasSingleBean(PreAuthenticatedUserDetailsService.class);
		});
	}
	
	@Test
	public void bguPreAuthenticationProvider_ShouldBeInApplicationContext() {
		runner.run(context -> {
			assertThat(context).hasSingleBean(BguPreAuthenticationProvider.class);
		});
	}
	
	@Test
	public void bguTokenAuthenticationFilter_ShouldBeInApplicationContext() {
		runner.run(context -> {
			assertThat(context).hasSingleBean(BguTokenAuthenticationFilter.class);
		});
	}
	
	/*
	 * OAuth2 Services
	 */
	
	@Test
	public void bguClientDetailsService_ShouldBeInApplicationContext() {
		runner.run(context -> {
			assertThat(context).hasSingleBean(BguClientDetailsService.class);
		});
	}
	
	@Test
	public void bguTokenStore_ShouldBeInApplicationContext() {
		runner.run(context -> {
			assertThat(context).hasSingleBean(BguTokenStore.class);
		});
	}
	
	@Test
	public void bguClientRegistrationRepository_ShouldBeInApplicationContext() {
		runner.run(context -> {
			assertThat(context).hasSingleBean(BguClientRegistrationRepository.class);
		});
	}
}
