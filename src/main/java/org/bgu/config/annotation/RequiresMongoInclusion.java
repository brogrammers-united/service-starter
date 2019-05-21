package org.bgu.config.annotation;

import org.bgu.config.condition.IncludeMongoCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @author William Gentry
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Conditional(IncludeMongoCondition.class)
public @interface RequiresMongoInclusion {
}
