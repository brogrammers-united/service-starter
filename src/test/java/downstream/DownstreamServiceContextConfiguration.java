package downstream;

import org.bgu.config.annotation.TheAppStarter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author William Gentry
 */
@SpringBootApplication
@TheAppStarter
@EnableDiscoveryClient
public class DownstreamServiceContextConfiguration {
}
