package javaConfig.spring.common;

import dao.common.JpaDAO;
import domain.common.Finder;
import domain.common.SimpleFinder;
import domain.users.UserAccount;
import domain.users.UserAccountValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import smartvalidation.validator.entityValidator.EntityValidatorFactory;
import smartvalidation.validator.entityValidator.ValidationContext;

import javax.persistence.EntityManager;


@Configuration
public class UsersConfig {

    @Autowired
    EntityManager entityManager;


    @Bean(name = "userAccountsDAO")
    public JpaRepository<UserAccount, Long> userAccountsDAO() {
        return new JpaDAO<UserAccount, Long>(entityManager, UserAccount.class);
    }

    @Bean
    public Finder<UserAccount, Long> userAccountsFinder(JpaRepository<UserAccount, Long> userAccountsDAO) {
        return new SimpleFinder<UserAccount, Long>(userAccountsDAO);
    }


//    @Bean
//    @Scope("prototype")
//    public UserAccount userAccount() {
//        return new UserAccount();
//    }

    @Bean
    public EntityValidatorFactory<UserAccount> userAccountOnCreateValidatorFactory() {
        return new UserAccountValidatorFactory.OnCreate(new ValidationContext(""));
    }

    @Bean
    public EntityValidatorFactory<UserAccount> userAccountOnUpdateValidatorFactory() {
        return new UserAccountValidatorFactory.OnUpdate(new ValidationContext(""));
    }


}
