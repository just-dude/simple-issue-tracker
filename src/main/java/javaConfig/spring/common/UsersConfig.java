package javaConfig.spring.common;

import dao.users.UserAccountsJpaDao;
import domain.users.UserAccount;
import domain.users.UserAccountsFinder;
import domain.users.validation.UserAccountValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import smartvalidation.validator.entityValidator.EntityValidatorFactory;
import smartvalidation.validator.entityValidator.ValidationContext;

import javax.persistence.EntityManager;


@Configuration
public class UsersConfig {

    @Autowired
    EntityManager entityManager;


    @Bean
    public UserAccountsJpaDao userAccountsDao() {
        return new UserAccountsJpaDao(entityManager);
    }

    @Bean
    public UserAccountsFinder userAccountsFinder(UserAccountsJpaDao userAccountsDao) {
        return new UserAccountsFinder(userAccountsDao);
    }

    @Bean
    public EntityValidatorFactory<UserAccount> userAccountOnCreateValidatorFactory() {
        return new UserAccountValidatorFactory.OnCreate(new ValidationContext("userAccount"));
    }

    @Bean
    public EntityValidatorFactory<UserAccount> userAccountOnUpdateValidatorFactory() {
        return new UserAccountValidatorFactory.OnUpdate(new ValidationContext("userAccount"));
    }

}
