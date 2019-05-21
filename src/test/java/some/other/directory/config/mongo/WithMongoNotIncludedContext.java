package some.other.directory.config.mongo;

import org.bgu.config.annotation.TheAppStarter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author William Gentry
 */
@SpringBootApplication(scanBasePackages="some.other.directory.config.mongo")
@TheAppStarter(includeMongo = false)
public class WithMongoNotIncludedContext {
}
