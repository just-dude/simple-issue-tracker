package javaConfig.spring.dev;

import common.beanFactory.BeanFactoryProvider;
import javaConfig.spring.common.PersistenceConfig;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.Clock;

@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy
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
