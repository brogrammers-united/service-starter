package some.other.directory.config.regular;

import org.bgu.config.annotation.TheAppStarter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="some.other.directory.config.regular")
@TheAppStarter
public class WithAnnotationContextConfiguration {

}
