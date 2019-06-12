package org.bgu.config.annotation;

import org.bgu.config.annotation.condition.IsDownstreamService;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author William Gentry
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Conditional(IsDownstreamService.class)
public @interface ConditionalOnDownstreamService {
}
