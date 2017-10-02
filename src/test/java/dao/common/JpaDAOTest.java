package dao.common;

import common.DBTestCase;
import common.beanFactory.BeanFactoryProvider;
import domain.users.AccountInfo;
import domain.users.Profile;
import domain.users.UserAccount;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.io.InputStream;
import java.util.List;

/**
 * Created by suslovai on 11.09.2017.
 */
public class JpaDAOTest extends DBTestCase {


    public JpaDAOTest() {
        super("JpaDAOTest");
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(getDataSetAsInputStream("testDataSet/users/UsersSetupDataset.xml"));
    }

    protected JpaRepository<UserAccount, Long> getDAO() {
        return (JpaRepository<UserAccount, Long>) BeanFactoryProvider.getBeanFactory().getBean("userAccountsDAO");
    }

    protected PlatformTransactionManager getTransactionManager() {
        return (PlatformTransactionManager) BeanFactoryProvider.getBeanFactory().getBean("transactionManager");
    }

    @Test
    public void testInsert() throws Exception {
        JpaRepository<UserAccount, Long> jpaDAO = getDAO();
        PlatformTransactionManager tm = getTransactionManager();
        UserAccount newUserAccount = new UserAccount(new AccountInfo("newUserLogin", "hashedPass", "salt")
                , new Profile("newUserName", "newUserSurname", "email@email.ru")
        );
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus ts = tm.getTransaction(def);
        UserAccount userAccountInPersistenceContext = null;

        userAccountInPersistenceContext = jpaDAO.save(newUserAccount);
        jpaDAO.flush();
        tm.commit(ts);

        assertNull(newUserAccount.getId());
        assertNotNull(userAccountInPersistenceContext.getId());

        IDataSet databaseDataSet = getConnection().createDataSet(new String[]{"test_issue_tracker.UserAccounts"});
        ITable actualTable = databaseDataSet.getTable("test_issue_tracker.UserAccounts");
        ITable filteredActualTable = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"id", "hashedPassword", "salt"});

        InputStream expectedDataSetInputStream = getDataSetAsInputStream("testDataSet/users/AfterInsertUserAccountExpectedDataset.xml");
        ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(expectedDataSetInputStream));
        expectedDataSet.addReplacementObject("[null]", null);
        ITable expectedTable = expectedDataSet.getTable("test_issue_tracker.UserAccounts");

        Assertion.assertEquals(expectedTable, filteredActualTable);

    }

    @Test
    public void testUpdate() throws Exception {
        JpaRepository<UserAccount, Long> jpaDAO = getDAO();
        PlatformTransactionManager tm = getTransactionManager();
        TransactionDefinition def = new DefaultTransactionDefinition();

        UserAccount userAccount = jpaDAO.findOne(1L);
        userAccount.getProfile().setName("newName");
        userAccount.getAccountInfo().setLogin("newLogin");
        TransactionStatus ts = tm.getTransaction(def);
        jpaDAO.save(userAccount);
        jpaDAO.flush();
        tm.commit(ts);

        IDataSet databaseDataSet = getConnection().createDataSet(new String[]{"test_issue_tracker.UserAccounts"});
        ITable actualTable = databaseDataSet.getTable("test_issue_tracker.UserAccounts");
        ITable filteredActualTable = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"id", "hashedPassword", "salt"});

        InputStream expectedDataSetInputStream = getDataSetAsInputStream("testDataSet/users/AfterUpdateUserAccountExpectedDataset.xml");
        ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(expectedDataSetInputStream));
        expectedDataSet.addReplacementObject("[null]", null);
        ITable expectedTable = expectedDataSet.getTable("test_issue_tracker.UserAccounts");

        Assertion.assertEquals(expectedTable, filteredActualTable);
    }

    @Test
    public void testDeleteById() throws Exception {
        JpaRepository<UserAccount, Long> jpaDAO = getDAO();
        PlatformTransactionManager tm = getTransactionManager();
        TransactionDefinition def = new DefaultTransactionDefinition();

        TransactionStatus ts = tm.getTransaction(def);
        jpaDAO.delete(1L);
        jpaDAO.flush();
        tm.commit(ts);

        IDataSet databaseDataSet = getConnection().createDataSet(new String[]{"test_issue_tracker.UserAccounts"});
        ITable actualTable = databaseDataSet.getTable("test_issue_tracker.UserAccounts");
        ITable filteredActualTable = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"id", "hashedPassword", "salt"});

        InputStream expectedDataSetInputStream = getDataSetAsInputStream("testDataSet/users/AfterDeleteUserAccountExpectedDataset.xml");
        ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(expectedDataSetInputStream));
        expectedDataSet.addReplacementObject("[null]", null);
        ITable expectedTable = expectedDataSet.getTable("test_issue_tracker.UserAccounts");

        Assertion.assertEquals(expectedTable, filteredActualTable);

    }

    @Test
    public void testDelete() throws Exception {
        JpaRepository<UserAccount, Long> jpaDAO = getDAO();
        PlatformTransactionManager tm = getTransactionManager();
        TransactionDefinition def = new DefaultTransactionDefinition();

        TransactionStatus ts = tm.getTransaction(def);
        UserAccount userAccount = jpaDAO.findOne(1L);
        jpaDAO.flush();
        tm.commit(ts);

        ts = tm.getTransaction(def);
        jpaDAO.delete(userAccount);
        jpaDAO.flush();
        tm.commit(ts);

        IDataSet databaseDataSet = getConnection().createDataSet(new String[]{"test_issue_tracker.UserAccounts"});
        ITable actualTable = databaseDataSet.getTable("test_issue_tracker.UserAccounts");
        ITable filteredActualTable = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"id", "hashedPassword", "salt"});

        InputStream expectedDataSetInputStream = getDataSetAsInputStream("testDataSet/users/AfterDeleteUserAccountExpectedDataset.xml");
        ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(expectedDataSetInputStream));
        expectedDataSet.addReplacementObject("[null]", null);
        ITable expectedTable = expectedDataSet.getTable("test_issue_tracker.UserAccounts");

        Assertion.assertEquals(expectedTable, filteredActualTable);

    }

    @Test
    public void testDeleteAll() throws Exception {
        JpaRepository<UserAccount, Long> jpaDAO = getDAO();
        PlatformTransactionManager tm = getTransactionManager();
        TransactionDefinition def = new DefaultTransactionDefinition();

        TransactionStatus ts = tm.getTransaction(def);
        jpaDAO.deleteAll();
        jpaDAO.flush();
        tm.commit(ts);

        IDataSet databaseDataSet = getConnection().createDataSet(new String[]{"test_issue_tracker.UserAccounts"});
        ITable actualTable = databaseDataSet.getTable("test_issue_tracker.UserAccounts");
        ITable filteredActualTable = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"id", "hashedPassword", "salt"});
        assertEquals(0, filteredActualTable.getRowCount());
    }

    @Test
    public void testGetOne() throws Exception {
        JpaRepository<UserAccount, Long> jpaDAO = (JpaRepository<UserAccount, Long>) BeanFactoryProvider.getBeanFactory().getBean("userAccountsDAO");
        UserAccount account = jpaDAO.getOne(1L);
        assertEquals(1L, (long) account.getId());
        assertEquals("name1", account.getProfile().getName());
        assertEquals("login1", account.getAccountInfo().getLogin());
    }

    @Test
    public void testCount() throws Exception {
        JpaRepository<UserAccount, Long> jpaDAO = (JpaRepository<UserAccount, Long>) BeanFactoryProvider.getBeanFactory().getBean("userAccountsDAO");
        long countOfAccounts = jpaDAO.count();
        assertEquals(3L, countOfAccounts);
    }

    @Test
    public void testFindAll() throws Exception {
        JpaRepository<UserAccount, Long> jpaDAO = (JpaRepository<UserAccount, Long>) BeanFactoryProvider.getBeanFactory().getBean("userAccountsDAO");
        List<UserAccount> accountsList = jpaDAO.findAll();
        assertEquals(3L, accountsList.size());
        assertEquals(1L, (long) accountsList.get(0).getId());
        assertEquals(2L, (long) accountsList.get(1).getId());
        assertEquals(3L, (long) accountsList.get(2).getId());
    }

    @Test
    public void testFindAllWithPageRequest() throws Exception {
        JpaRepository<UserAccount, Long> jpaDAO = (JpaRepository<UserAccount, Long>) BeanFactoryProvider.getBeanFactory().getBean("userAccountsDAO");
        Page<UserAccount> accountsPage = jpaDAO.findAll(new PageRequest(0, 2, Sort.Direction.DESC, "id"));
        List<UserAccount> userAccountList = accountsPage.getContent();
        assertEquals(3L, accountsPage.getTotalElements());
        assertEquals(2L, accountsPage.getTotalPages());
        assertEquals(2L, accountsPage.getSize());
        assertEquals(3L, (long) userAccountList.get(0).getId());
        assertEquals(2L, (long) userAccountList.get(1).getId());
    }
}