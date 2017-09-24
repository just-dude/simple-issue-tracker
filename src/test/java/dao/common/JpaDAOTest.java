package dao.common;

import common.SpringContextTestCase;
import common.beanFactory.BeanFactoryProvider;
import domain.users.AccountInfo;
import domain.users.Profile;
import domain.users.UserAccount;
import org.junit.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * Created by suslovai on 11.09.2017.
 */
public class JpaDAOTest extends SpringContextTestCase {
    @Test
    public void save() throws Exception {
        JpaRepository<UserAccount, Long> jpaDAO = (JpaRepository<UserAccount, Long>) BeanFactoryProvider.getBeanFactory().getBean("userAccountsDAO");
        PlatformTransactionManager tm = (PlatformTransactionManager) BeanFactoryProvider.getBeanFactory().getBean("transactionManager");
        UserAccount firstUA = new UserAccount(new AccountInfo("login1", "hashedPass1", "salt1")
                , new Profile("name1", "surname1", "email1@email.ru")
        );
        UserAccount secondUA = new UserAccount(16L, new AccountInfo("login2", "hashedPass2", "salt2")
                , new Profile("name2", "surname2", "email2@email.ru")
        );
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus ts = tm.getTransaction(def);
        try {
            UserAccount pcUA = jpaDAO.save(firstUA);
            System.out.println("Entity not in persistece context: " + firstUA);
            System.out.println("Entity in persistece context: " + pcUA);
            pcUA.getId();
            jpaDAO.flush();

            tm.commit(ts);
        } catch (Exception e) {
            System.out.println("Error in creating record, rolling back");
            tm.rollback(ts);
        }
        ts = tm.getTransaction(def);
        try {
            UserAccount pcUA = jpaDAO.save(secondUA);
            System.out.println("Entity not in persistece context: " + secondUA);
            System.out.println("Entity in persistece context: " + pcUA);
            pcUA.getId();
            jpaDAO.flush();

            tm.commit(ts);
        } catch (Exception e) {
            System.out.println("Error in creating record, rolling back");
            System.out.println(e);
            tm.rollback(ts);
        }
    }

    @Test
    public void testGetEntityTypeClass() throws Exception {
        JpaRepository<UserAccount, Long> jpaDAO = (JpaRepository<UserAccount, Long>) BeanFactoryProvider.getBeanFactory().getBean("userAccountsDAO");
        UserAccount account = jpaDAO.getOne(10L);
        System.out.println(account);

    }
}