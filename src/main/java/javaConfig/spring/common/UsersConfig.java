package javaConfig.spring.common;

import dao.common.JpaDAO;
import domain.common.Finder;
import domain.common.SimpleFinder;
import domain.users.UserAccount;
import domain.users.UserAccountValidatorFactory;
import domain.users.UserGroup;
import domain.users.UserGroupValidatorFactory;
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


    @Bean
    public JpaRepository<UserAccount, Long> userAccountsDAO() {
        return new JpaDAO<UserAccount, Long>(entityManager, UserAccount.class);
    }

    @Bean
    public Finder<UserAccount, Long> userAccountsFinder(JpaRepository<UserAccount, Long> userAccountsDAO) {
        return new SimpleFinder<UserAccount, Long>(userAccountsDAO);
    }

    @Bean
    public EntityValidatorFactory<UserAccount> userAccountOnCreateValidatorFactory() {
        return new UserAccountValidatorFactory.OnCreate(new ValidationContext("userAccount"));
    }

    @Bean
    public EntityValidatorFactory<UserAccount> userAccountOnUpdateValidatorFactory() {
        return new UserAccountValidatorFactory.OnUpdate(new ValidationContext("userAccount"));
    }


    @Bean
    public JpaRepository<UserGroup, Long> userGroupsDAO() {
        return new JpaDAO<UserGroup, Long>(entityManager, UserGroup.class);
    }

    @Bean
    public Finder<UserGroup, Long> userGroupsFinder(JpaRepository<UserGroup, Long> userGroupsDAO) {
        return new SimpleFinder<UserGroup, Long>(userGroupsDAO);
    }


    @Bean
    public EntityValidatorFactory<UserGroup> userGroupOnCreateValidatorFactory() {
        return new UserGroupValidatorFactory.OnCreate(new ValidationContext("userGroup"));
    }

    @Bean
    public EntityValidatorFactory<UserGroup> userGroupOnUpdateValidatorFactory() {
        return new UserGroupValidatorFactory.OnUpdate(new ValidationContext("userGroup"));
    }

    /*
    <bean id="UsersServiceBasedAuthenticatingRealm" class="ru.zabgu.service.concreteService.security.realm.UsersServiceBasedAuthenticatingRealm">
        <property name="credentialsMatcher">
            <!-- The 'bootstrapDataPopulator' Sha256 hashes the password
                 (using the username as the salt) then base64 encodes it: -->
            <bean class="org.apache.shiro.authc.credential.HashedCredentialsMatcher">
                <property name="hashAlgorithmName" value="SHA-256"/>
                <property name="hashIterations" value="256"/>
                <!-- true means hex encoded, false means base64 encoded -->
                <property name="storedCredentialsHexEncoded" value="false"/>
            </bean>
        </property>
    </bean>*/


}
