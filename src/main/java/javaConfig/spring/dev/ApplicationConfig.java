package javaConfig.spring.dev;

import common.beanFactory.BeanFactoryProvider;
import javaConfig.spring.common.PersistenceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import java.time.Clock;

@Configuration
@Import({javaConfig.spring.common.UsersConfig.class, PersistenceConfig.class})
@PropertySource(value = "classpath:сonfig/spring/persistence-dev.properties")
public class ApplicationConfig {
    @Bean
    public BeanFactoryProvider beanFactoryProvider() {
        return new BeanFactoryProvider();
    }

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }
}
