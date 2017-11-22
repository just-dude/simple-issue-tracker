package domain.users;

import common.DBTestCase;
import domain.common.exception.EntityWithSuchIdDoesNotExistsBusinessException;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;
import smartvalidation.exception.ConstraintValidationException;

import java.io.InputStream;

/**
 * Created by SuslovAI on 04.10.2017.
 */
public class UserAccountTest extends DBTestCase {

    public UserAccountTest() {
        super("UserAccountTest");
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(getDataSetAsInputStream("testDataSet/users/UsersSetupDataset.xml"));
    }

    @Test
    public void isValidAndGetConstraintsViolations() throws Exception {
        UserAccount validUserAccount = new UserAccount(
                new AccountInfo("login", "passswordWithBigLength", null),
                new Profile("name", "surname", "email@mail.ru"),
                UserGroup.CommonUser
        );
        assertTrue(validUserAccount.isValid());
        try {
            validUserAccount.getConstraintsViolations();
            fail();
        } catch (ConstraintValidationException e) {
        }
        UserAccount invalidUserAccount = new UserAccount(
                new AccountInfo("", "/.", null),
                new Profile(",.", "!sadf", "mail.ru"),
                UserGroup.CommonUser
        );
        assertFalse(invalidUserAccount.isValid());
        System.out.println(invalidUserAccount.getConstraintsViolations());
        assertEquals(5, invalidUserAccount.getConstraintsViolations().size());
    }

    @Test
    public void insert() throws Exception {
        UserAccount userAccount = new UserAccount(
                new AccountInfo("newUserLogin", "passswordWithBigLength", null),
                new Profile("newUserName", "newUserSurname", "email@email.ru"),
                UserGroup.CommonUser
        );
        UserAccount savedAccount = userAccount.save();
        assertNotNull(savedAccount.getId());

        IDataSet databaseDataSet = getConnection().createDataSet(new String[]{"test_issue_tracker.UserAccounts"});
        ITable actualTable = databaseDataSet.getTable("test_issue_tracker.UserAccounts");
        ITable filteredActualTable = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"id", "hashedPassword", "salt"});

        InputStream expectedDataSetInputStream = getDataSetAsInputStream("testDataSet/users/AfterInsertUserAccountExpectedDataset.xml");
        ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(expectedDataSetInputStream));
        ITable expectedTable = expectedDataSet.getTable("test_issue_tracker.UserAccounts");

        Assertion.assertEquals(expectedTable, filteredActualTable);
    }

    @Test
    public void update() throws Exception {
        UserAccount userAccount = new UserAccount(
                1L,
                null,
                new Profile("newName", "surname", "email1@mail.ml"),
                UserGroup.CommonUser
        );
        UserAccount savedAccount = userAccount.save();
        assertNotNull(savedAccount.getId());

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
    public void updateNotExistEntity() throws Exception {
        UserAccount userAccount = new UserAccount(
                10L,
                null,
                new Profile("newName", "surname", "email1@mail.ml"),
                UserGroup.CommonUser
        );
        try {
            UserAccount savedAccount = userAccount.save();
            fail();
        } catch (EntityWithSuchIdDoesNotExistsBusinessException e) {
            System.out.println(e);
        }
    }

    @Test
    public void remove() throws Exception {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(1L);
        userAccount.remove();

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
    public void testFindByLogin(){

    }


}