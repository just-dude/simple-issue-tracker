package javaConfig.spring.common;

import dao.common.JpaDAO;
import domain.users.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;

@Configuration
public class UsersConfig {

    @Autowired
    EntityManager entityManager;


    @Bean(name = "userAccountsDAO")
    public JpaRepository<UserAccount, Long> userAccountsDAO() {
        return new JpaDAO<UserAccount, Long>(entityManager, UserAccount.class);
    }

}
