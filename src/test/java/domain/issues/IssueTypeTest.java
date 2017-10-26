package domain.issues;

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

public class IssueTypeTest extends DBTestCase {

    public IssueTypeTest() {
        super("IssueTypeTest");
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(getDataSetAsInputStream("testDataSet/issues/IssueTypesSetupDataset.xml"));
    }

    @Test
    public void isValidAndGetConstraintsViolations() throws Exception {
        IssueType validIssueType = new IssueType(null,"firstIssueType",null);
        assertTrue(validIssueType.isValid());
        try {
            validIssueType.getConstraintsViolations();
            fail();
        } catch (ConstraintValidationException e) {
        }
        IssueType invalidIssueType = new IssueType(null,"first.IssueType",null);
        assertFalse(invalidIssueType.isValid());
        System.out.println(invalidIssueType.getConstraintsViolations());
        assertEquals(1, invalidIssueType.getConstraintsViolations().size());
    }

    /*@Test
    public void insert() throws Exception {
        UserAccount issueAccount = new UserAccount(
                new AccountInfo("newUserLogin", "passswordWithBigLength", null),
                new Profile("newUserName", "newUserSurname", "email@email.ru"),
                UserGroup.CommonUser
        );
        UserAccount savedAccount = issueAccount.save();
        assertNotNull(savedAccount.getId());

        IDataSet databaseDataSet = getConnection().createDataSet(new String[]{"test_issue_tracker.UserAccounts"});
        ITable actualTable = databaseDataSet.getTable("test_issue_tracker.UserAccounts");
        ITable filteredActualTable = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"id", "hashedPassword", "salt"});

        InputStream expectedDataSetInputStream = getDataSetAsInputStream("testDataSet/issues/AfterInsertUserAccountExpectedDataset.xml");
        ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(expectedDataSetInputStream));
        ITable expectedTable = expectedDataSet.getTable("test_issue_tracker.UserAccounts");

        Assertion.assertEquals(expectedTable, filteredActualTable);
    }

    @Test
    public void update() throws Exception {
        UserAccount issueAccount = new UserAccount(
                1L,
                null,
                new Profile("newName", "surname", "email1@mail.ml"),
                UserGroup.CommonUser
        );
        UserAccount savedAccount = issueAccount.save();
        assertNotNull(savedAccount.getId());

        IDataSet databaseDataSet = getConnection().createDataSet(new String[]{"test_issue_tracker.UserAccounts"});
        ITable actualTable = databaseDataSet.getTable("test_issue_tracker.UserAccounts");
        ITable filteredActualTable = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"id", "hashedPassword", "salt"});

        InputStream expectedDataSetInputStream = getDataSetAsInputStream("testDataSet/issues/AfterUpdateUserAccountExpectedDataset.xml");
        ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(expectedDataSetInputStream));
        expectedDataSet.addReplacementObject("[null]", null);
        ITable expectedTable = expectedDataSet.getTable("test_issue_tracker.UserAccounts");

        Assertion.assertEquals(expectedTable, filteredActualTable);
    }

    @Test
    public void updateNotExistEntity() throws Exception {
        UserAccount issueAccount = new UserAccount(
                10L,
                null,
                new Profile("newName", "surname", "email1@mail.ml"),
                UserGroup.CommonUser
        );
        try {
            UserAccount savedAccount = issueAccount.save();
            fail();
        } catch (EntityWithSuchIdDoesNotExistsBusinessException e) {
            System.out.println(e);
        }
    }

    @Test
    public void remove() throws Exception {
        UserAccount issueAccount = new UserAccount();
        issueAccount.setId(1L);
        issueAccount.remove();

        IDataSet databaseDataSet = getConnection().createDataSet(new String[]{"test_issue_tracker.UserAccounts"});
        ITable actualTable = databaseDataSet.getTable("test_issue_tracker.UserAccounts");
        ITable filteredActualTable = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"id", "hashedPassword", "salt"});

        InputStream expectedDataSetInputStream = getDataSetAsInputStream("testDataSet/issues/AfterDeleteUserAccountExpectedDataset.xml");
        ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(expectedDataSetInputStream));
        expectedDataSet.addReplacementObject("[null]", null);
        ITable expectedTable = expectedDataSet.getTable("test_issue_tracker.UserAccounts");

        Assertion.assertEquals(expectedTable, filteredActualTable);
    }*/


}