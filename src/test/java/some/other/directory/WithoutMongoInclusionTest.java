package some.other.directory;

import static org.assertj.core.api.Assertions.*;

import com.mongodb.MongoClient;
import org.bgu.config.MongoConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import some.other.directory.config.mongo.WithMongoNotIncludedContext;

/**
 * @author William Gentry
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = WithMongoNotIncludedContext.class)
public class WithoutMongoInclusionTest {

    @Autowired
    private ApplicationContext context;

    private ApplicationContextRunner runner;

    @Before
    public void setUpApplicationContextRunner() {
        runner = new ApplicationContextRunner().withParent(context);
    }

    @Test
    public void applicationMongoClient_ShouldNotBeIncluded() {
        runner.run(context -> {
           assertThat(context).hasSingleBean(MongoClient.class);
           assertThat(context.getBean("oauthClient")).isNotNull();
        });
    }

    @Test
    public void applicationMongoTemplate_ShouldNotBeIncluded() {
        runner.run(context -> {
           assertThat(context).hasSingleBean(MongoTemplate.class);
           assertThat(context.getBean("oauthMongoTemplate")).isNotNull();
        });
    }
}
