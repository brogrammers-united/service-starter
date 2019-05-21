package some.other.directory;

import static org.assertj.core.api.Assertions.*;

import org.bgu.security.BguPreAuthenticationProvider;
import org.bgu.security.BguTokenAuthenticationFilter;
import org.bgu.security.PreAuthenticatedUserDetailsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author William Gentry
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = WithoutSecurityInclusionTest.class)
public class WithoutSecurityInclusionTest {

    @Autowired
    private ApplicationContext context;

    private ApplicationContextRunner runner;

    @Before
    public void setUpApplicationContextRunner() {
        runner = new ApplicationContextRunner().withParent(context);
    }

    @Test
    public void authenticationProvider_ShouldNotBePresent() {
        runner.run(context -> {
            assertThat(context).doesNotHaveBean(BguPreAuthenticationProvider.class);
        });
    }

    @Test
    public void tokenFilter_ShouldNotBePresent() {
        runner.run(context -> {
            assertThat(context).doesNotHaveBean(BguTokenAuthenticationFilter.class);
        });
    }

    @Test
    public void preUserDetailsService_ShouldNotBePresent() {
        runner.run(context -> {
            assertThat(context).doesNotHaveBean(PreAuthenticatedUserDetailsService.class);
        });
    }

    @Test
    public void passwordEncoder_ShouldNotBePresent() {
        runner.run(context -> {
            assertThat(context).doesNotHaveBean(PasswordEncoder.class);
        });
    }
}
