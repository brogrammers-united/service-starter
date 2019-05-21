package org.bgu.config.condition;

import org.bgu.config.annotation.TheAppStarter;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author William Gentry
 */
public class IncludeSecurityCondition implements ConfigurationCondition {

    @Override
    public ConfigurationPhase getConfigurationPhase() {
        return ConfigurationPhase.PARSE_CONFIGURATION;
    }

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> beans = context.getBeanFactory().getBeansWithAnnotation(TheAppStarter.class);

        String beanName = beans.keySet().stream().filter(name -> StringUtils.hasText(name)).reduce("", (a, b) -> a += b);

        return beans.get(beanName).getClass().getAnnotation(TheAppStarter.class).includeSecurity();
    }
}
