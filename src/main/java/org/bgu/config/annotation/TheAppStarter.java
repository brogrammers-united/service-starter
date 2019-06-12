package org.bgu.config.annotation;

import org.bgu.config.TheAppAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(TheAppAutoConfiguration.class)
public @interface TheAppStarter {

    boolean isGateway() default false;
}
