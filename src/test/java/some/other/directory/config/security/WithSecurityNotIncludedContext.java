package some.other.directory.config.security;

import org.bgu.config.annotation.TheAppStarter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author William Gentry
 */
@SpringBootApplication(scanBasePackages="some.other.directory.config.security")
@TheAppStarter(includeSecurity = false)
public class WithSecurityNotIncludedContext {
}
