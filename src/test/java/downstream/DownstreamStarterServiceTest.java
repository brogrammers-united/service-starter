package downstream;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bgu.config.MongoConfig;
import org.bgu.config.PropertiesConfiguration;
import org.bgu.config.WebConfig;
import org.bgu.config.WebSecurityConfig;
import org.bgu.config.properties.KeyStoreProperties;
import org.bgu.config.properties.MongoProperties;
import org.bgu.config.properties.WebClientProperties;
import org.bgu.exception.ApplicationExceptionHandler;
import org.bgu.oauth.service.BguClientDetailsService;
import org.bgu.oauth.service.BguClientRegistrationRepository;
import org.bgu.oauth.service.BguTokenStore;
import org.bgu.oauth.service.BguUserDetailsServiceImpl;
import org.bgu.oauth.service.interfaces.BguUserDetailsService;
import org.bgu.repository.AccessTokenRepository;
import org.bgu.repository.ApplicationUserRepository;
import org.bgu.repository.BguClientDetailsRepository;
import org.bgu.repository.RepositoryBeans;
import org.bgu.security.*;
import org.bgu.web.TestController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author William Gentry
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = DownstreamServiceContextConfiguration.class)
public class DownstreamStarterServiceTest {

    @Autowired
    private ApplicationContext context;

    private ApplicationContextRunner runner;

    @Before
    public void setUpApplicationContextRunner() {
        runner = new ApplicationContextRunner().withParent(context);
    }

    @Test
    public void webSecurityConfiguration_ShouldBeIncluded() {
        runner.run(context -> {
            assertThat(context).hasSingleBean(WebSecurityConfig.class);
        });
    }

    @Test
    public void bguPreAuthenticationProvider_ShouldBeIncluded() {
        runner.run(context -> {
            assertThat(context).hasSingleBean(BguPreAuthenticationProvider.class);
        });
    }

    @Test
    public void bguTokenAuthenticationFilter_ShouldBeIncluded() {
        runner.run(context -> {
            assertThat(context).hasSingleBean(BguTokenAuthenticationFilter.class);
        });
    }

    @Test
    public void preAuthenticatedUserDetailsService_ShouldBeIncluded() {
        runner.run(context -> {
            assertThat(context).hasSingleBean(PreAuthenticatedUserDetailsService.class);
        });
    }

    @Test
    public void ghAuthTokenController_ShouldBeIncluded() {
        runner.run(context -> {
            assertThat(context).hasSingleBean(TestController.class);
        });
    }

    /*
           OAuth2 Inclusion Tests
    */
    @Test
    public void jwtTokenStore_ShouldBeIncluded() {
        runner.run(context -> {
            assertThat(context).hasSingleBean(JwtTokenStore.class);
        });
    }

    @Test
    public void tokenEnhancerChain_ShouldBeIncluded() {
        runner.run(context -> {
            assertThat(context).hasSingleBean(TokenEnhancer.class);
            assertThat(context.getBean("tokenEnhancerChain")).isSameAs(context.getBean(TokenEnhancer.class));
        });
    }

    @Test
    public void oauth2RequestFactory_ShouldBeIncluded() {
        runner.run(context -> {
            assertThat(context).hasSingleBean(OAuth2RequestFactory.class);
        });
    }

    @Test
    public void bguClientDetailsService_ShouldBeIncluded() {
        runner.run(context -> {
            assertThat(context).hasSingleBean(ClientDetailsService.class);
            assertThat(context.getBean(ClientDetailsService.class)).isInstanceOf(BguClientDetailsService.class);
        });
    }

    @Test
    public void bguClientRegistrationService_ShouldBeIncluded() {
        runner.run(context -> {
            assertThat(context).hasSingleBean(ClientRegistrationRepository.class);
            assertThat(context.getBean(ClientRegistrationRepository.class)).isInstanceOf(BguClientRegistrationRepository.class);
        });
    }

    @Test
    public void bguTokenStore_ShouldBeIncluded() {
        runner.run(context -> {
            assertThat(context).hasSingleBean(BguTokenStore.class);
        });
    }

    @Test
    public void bguUserDetailsService_ShouldBeIncluded() {
        runner.run(context -> {
            assertThat(context).hasSingleBean(BguUserDetailsService.class);
            assertThat(context.getBean(BguUserDetailsService.class)).isInstanceOf(BguUserDetailsServiceImpl.class);
        });
    }

    @Test
    public void httpCookieOAuth2AuthorizationRequestRepository_ShouldBeIncluded() {
        runner.run(context -> {
            assertThat(context).hasSingleBean(HttpCookieOAuth2AuthorizationRequestRepository.class);
        });
    }

    /*
            @ConfigurationProperties Inclusion Tests
     */
    @Test
    public void keyStoreProperties_ShouldBeIncluded() {
        runner.run(context -> {
            assertThat(context).hasSingleBean(KeyStoreProperties.class);
        });
    }

    @Test
    public void mongoProperties_ShouldBeIncluded() {
        runner.run(context -> {
            assertThat(context).hasSingleBean(MongoProperties.class);
        });
    }

    @Test
    public void webClientProperties_ShouldBeIncluded() {
        runner.run(context -> {
           assertThat(context).hasSingleBean(WebClientProperties.class);
        });
    }

    /*
            @Configuration Inclusion Tests
     */
    @Test
    public void mongoConfig_ShouldBeIncluded() {
        runner.run(context -> {
            assertThat(context).hasSingleBean(MongoConfig.class);
            assertThat(context).hasSingleBean(MongoTemplate.class);
            assertThat(context.getBean("oauthMongoTemplate")).isSameAs(context.getBean(MongoTemplate.class));
        });
    }

    @Test
    public void propertiesConfiguration_ShouldBeIncluded() {
        runner.run(context -> {
            assertThat(context).hasSingleBean(PropertiesConfiguration.class);
        });
    }

    @Test
    public void webConfig_ShouldBeIncluded() {
        runner.run(context -> {
            assertThat(context).hasSingleBean(WebConfig.class);
            assertThat(context).hasSingleBean(ObjectMapper.class);
            assertThat(context).hasSingleBean(MessageSource.class);
            assertThat(context).hasSingleBean(RestTemplate.class);
            assertThat(context).hasSingleBean(WebClient.Builder.class);
        });
    }

    @Test
    public void repositoryBeans_ShouldBeIncluded() {
        runner.run(context -> {
            assertThat(context).hasSingleBean(RepositoryBeans.class);
            assertThat(context).hasSingleBean(AccessTokenRepository.class);
            assertThat(context).hasSingleBean(ApplicationUserRepository.class);
            assertThat(context).hasSingleBean(BguClientDetailsRepository.class);
        });
    }

    @Test
    public void securityBeans_ShouldBeIncluded() {
        runner.run(context -> {
            assertThat(context).hasSingleBean(SecurityBeans.class);
            assertThat(context).hasSingleBean(KeyStoreService.class);
            assertThat(context.getBean(KeyStoreService.class)).isInstanceOf(KeyStoreServiceImpl.class);
            assertThat(context).hasSingleBean(TokenService.class);
            assertThat(context.getBean(TokenService.class)).isInstanceOf(TokenServiceImpl.class);
        });
    }

    /*
            Additional Beans Inclusion
     */
    @Test
    public void applicationExceptionHandler_ShouldBeIncluded() {
        runner.run(context -> {
            assertThat(context).hasSingleBean(ApplicationExceptionHandler.class);
        });
    }

    @Test
    public void passwordEncoder_ShouldBeIncluded_AndBCrypt() {
        runner.run(context -> {
            assertThat(context).hasSingleBean(PasswordEncoder.class);
            assertThat(context.getBean(PasswordEncoder.class)).isInstanceOf(BCryptPasswordEncoder.class);
        });
    }
}
