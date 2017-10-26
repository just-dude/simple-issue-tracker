package javaConfig.spring.test;

import common.beanFactory.BeanFactoryProvider;
import javaConfig.spring.common.IssuesConfig;
import javaConfig.spring.common.PersistenceConfig;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@Configuration
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
@EnableAspectJAutoProxy
@Import({javaConfig.spring.common.UsersConfig.class, PersistenceConfig.class, SecurityConfig.class,IssuesConfig.class})
@PropertySource(value = "classpath:сonfig/spring/persistence-test.properties")
public class ApplicationConfig {

    private static final long fixedTimeForTestsInEpochMilliseconds = 1451606400; // Fri, 01 Jan 2016 00:00:00 GMT

    @Bean
    public BeanFactoryProvider beanFactoryProvider() {
        return new BeanFactoryProvider();
    }

    @Bean
    public Clock clock() {
        return Clock.fixed(Instant.ofEpochSecond(fixedTimeForTestsInEpochMilliseconds), ZoneId.of("UTC"));
    }
}
