package org.bgu.config.annotation;

import org.bgu.config.condition.IncludeSecurityCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Repository;

import java.lang.annotation.*;

/**
 * @author William Gentry
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Conditional(IncludeSecurityCondition.class)
public @interface RequiresSecurityInclusion {
}
