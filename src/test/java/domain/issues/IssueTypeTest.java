package domain.issues;

import common.DBTestCase;
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
        return new FlatXmlDataSetBuilder().build(getDataSetAsInputStream("testDataSet/issues/issueTypes/IssueTypesSetupDataset.xml"));
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

    @Test
    public void insert() throws Exception {
        IssueType issueType = new IssueType(null,"newIssueTypeName",null);
        IssueType savedIssueTYpe = issueType.save();
        assertNotNull(savedIssueTYpe.getId());

        IDataSet databaseDataSet = getConnection().createDataSet(new String[]{"test_issue_tracker.IssueTypes"});
        ITable actualTable = databaseDataSet.getTable("test_issue_tracker.IssueTypes");
        ITable filteredActualTable = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"id"});
        InputStream expectedDataSetInputStream = getDataSetAsInputStream("testDataSet/issues/issueTypes/AfterInsertIssueTypeExpectedDataset.xml");
        ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(expectedDataSetInputStream));
        ITable expectedTable = expectedDataSet.getTable("test_issue_tracker.IssueTypes");

        Assertion.assertEquals(expectedTable, filteredActualTable);
    }

    @Test
    public void update() throws Exception {
        IssueType issueType = new IssueType(1L,"updatedIssueTypeName",null);
        issueType.save();

        IDataSet databaseDataSet = getConnection().createDataSet(new String[]{"test_issue_tracker.IssueTypes"});
        ITable actualTable = databaseDataSet.getTable("test_issue_tracker.IssueTypes");
        ITable filteredActualTable = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"id"});

        InputStream expectedDataSetInputStream = getDataSetAsInputStream("testDataSet/issues/issueTypes/AfterUpdateIssueTypeExpectedDataset.xml");
        ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(expectedDataSetInputStream));
        expectedDataSet.addReplacementObject("[null]", null);
        ITable expectedTable = expectedDataSet.getTable("test_issue_tracker.IssueTypes");

        Assertion.assertEquals(expectedTable, filteredActualTable);
    }

    @Test
    public void remove() throws Exception {
        IssueType issueType = new IssueType();
        issueType.setId(1L);
        issueType.remove();

        IDataSet databaseDataSet = getConnection().createDataSet(new String[]{"test_issue_tracker.IssueTypes"});
        ITable actualTable = databaseDataSet.getTable("test_issue_tracker.IssueTypes");
        ITable filteredActualTable = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"id"});

        InputStream expectedDataSetInputStream = getDataSetAsInputStream("testDataSet/issues/issueTypes/AfterDeleteIssueTypeExpectedDataset.xml");
        ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(expectedDataSetInputStream));
        expectedDataSet.addReplacementObject("[null]", null);
        ITable expectedTable = expectedDataSet.getTable("test_issue_tracker.IssueTypes");

        Assertion.assertEquals(expectedTable, filteredActualTable);
    }


}