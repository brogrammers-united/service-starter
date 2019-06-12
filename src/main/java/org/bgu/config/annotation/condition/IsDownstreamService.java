package org.bgu.config.annotation.condition;

import org.bgu.config.annotation.TheAppStarter;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author William Gentry
 */
public class IsDownstreamService implements ConfigurationCondition {

    @Override
    public ConfigurationPhase getConfigurationPhase() {
        return ConfigurationPhase.PARSE_CONFIGURATION;
    }

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String[] names = context.getBeanFactory().getBeanNamesForAnnotation(TheAppStarter.class);
        if (names.length > 1)
            throw new RuntimeException("ApplicationContext found multiple @TheAppStarter classes. Select 1 class");
        return !context.getBeanFactory().getBean(names[0]).getClass().getAnnotation(TheAppStarter.class).isGateway();
    }
}
