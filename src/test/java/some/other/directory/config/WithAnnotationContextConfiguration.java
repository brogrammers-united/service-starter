package some.other.directory.config;

import org.bgu.config.annotation.TheAppStarter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="some.other.directory.config")
@TheAppStarter
public class WithAnnotationContextConfiguration {

}
