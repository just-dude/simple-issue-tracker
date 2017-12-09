package domain.issues;

import com.google.common.collect.Sets;
import common.DBTestCase;
import common.beanFactory.BeanFactoryProvider;
import domain.common.Finder;
import domain.common.exception.DataIntegrityViolationBusinessException;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;
import smartvalidation.exception.ConstraintValidationException;

import java.io.InputStream;
import java.math.BigInteger;
import java.util.*;

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
        IssueType validIssueType = new IssueType(null,"firstIssueType",new ArrayList());
        assertTrue(validIssueType.isValid());
        try {
            validIssueType.getConstraintsViolations();
            fail();
        } catch (ConstraintValidationException e) {
        }
        IssueType invalidIssueType = new IssueType(null,"first.IssueType",null);
        assertFalse(invalidIssueType.isValid());
        System.out.println(invalidIssueType.getConstraintsViolations());
        assertEquals(2, invalidIssueType.getConstraintsViolations().size());
    }

    @Test
    public void testInsert() throws Exception {
        IssueType issueType=new IssueType(null, "newIssueTypeName",null);
        issueType.addIssueState(
                new IssueState(null, "newInitialState", true, false, null,
                        null
                ));
        issueType.addIssueState(
                new IssueState(null, "newFirstTransitionState", false, false, null,
                        null
                ));
        issueType.addIssueState(
                new IssueState(null, "newSecondTransitionState", false, false, null,
                        null
                ));
        issueType.addIssueState(
                new IssueState(null, "newFinishState", false, true, null,
                        new IssueType(2L, null, null)
                ));
        Map<Integer, List<Integer>> issueStateIndexToTransferIssueStateIndexesListMap = new HashMap<>();
        issueStateIndexToTransferIssueStateIndexesListMap.put(0, Arrays.asList(1));
        issueStateIndexToTransferIssueStateIndexesListMap.put(1, Arrays.asList(2));
        issueStateIndexToTransferIssueStateIndexesListMap.put(2, Arrays.asList(3));

        issueType.save(
                issueStateIndexToTransferIssueStateIndexesListMap
        );

        IDataSet databaseDataSet = getConnection().createDataSet(
                new String[]{"test_issue_tracker.IssueTypes","test_issue_tracker.IssueStates","test_issue_tracker.IssueStates_IssueStates"});
        ITable actualTable = databaseDataSet.getTable("test_issue_tracker.IssueStates");
        ITable filteredActualTable = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"id"});
        InputStream expectedDataSetInputStream = getDataSetAsInputStream("testDataSet/issues/issueTypes/AfterInsertIssueTypeExpectedDataset.xml");
        ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(expectedDataSetInputStream));
        expectedDataSet.addReplacementSubstring("[newIssueTypeId]",issueType.getId().toString());
        ITable expectedTable = expectedDataSet.getTable("test_issue_tracker.IssueStates");

        Assertion.assertEquals(expectedTable, filteredActualTable);

        actualTable = databaseDataSet.getTable("test_issue_tracker.IssueTypes");
        filteredActualTable = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"id"});
        expectedTable = expectedDataSet.getTable("test_issue_tracker.IssueTypes");

        Assertion.assertEquals(expectedTable, filteredActualTable);

        ITable issueStateTransferStates = databaseDataSet.getTable("test_issue_tracker.IssueStates_IssueStates");
        for(int i=2;i<5;i++) {
            Long sourceIssueState=((BigInteger)issueStateTransferStates.getValue(i,"IssueState_id")).longValue();
            Long toTransferIssueState=((BigInteger)issueStateTransferStates.getValue(i,"issueStatesToTransition_id")).longValue();
            assertEquals((long)sourceIssueState+1L,(long)toTransferIssueState);
        }
    }

    @Test
    public void testFindAllByIssueType(){
        Finder<IssueType,Long> issueTypesFinder = (Finder<IssueType,Long>) BeanFactoryProvider.getBeanFactory().getBean("issueTypesFinder");
        IssueType issueType = issueTypesFinder.findOneWithInitPaths(1l,Sets.newHashSet(IssueType.ISSUE_STATES_INIT_PATH));
        List<IssueState> issueStateList = issueType.getIssueStates();
        assertEquals(3,issueStateList.size());
    }


    @Test
    public void update() throws Exception {
        IssueType issueType=new IssueType(1L,"updatedIssueTypeName",null);
        issueType.addIssueState(
                new IssueState(1L, "initialIssueState", true, false, null,
                        null
                ));
        issueType.addIssueState(
                new IssueState(null, "newSecondTransitionState", false, false, null,
                        null
                ));
        issueType.addIssueState(
                new IssueState(null, "newFinishState", false, true, null,null
                ));
        Map<Integer, List<Integer>> issueStateIndexToTransferIssueStateIndexesListMap = new HashMap<>();
        issueStateIndexToTransferIssueStateIndexesListMap.put(0, Arrays.asList(1));
        issueStateIndexToTransferIssueStateIndexesListMap.put(1, Arrays.asList(2));

        issueType.save(issueStateIndexToTransferIssueStateIndexesListMap);

        IDataSet databaseDataSet = getConnection().createDataSet(
                new String[]{"test_issue_tracker.IssueTypes","test_issue_tracker.IssueStates","test_issue_tracker.IssueStates_IssueStates"});
        ITable actualTable = databaseDataSet.getTable("test_issue_tracker.IssueTypes");
        ITable filteredActualTable = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"id"});
        InputStream expectedDataSetInputStream = getDataSetAsInputStream("testDataSet/issues/issueTypes/AfterUpdateIssueTypeExpectedDataset.xml");
        ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(expectedDataSetInputStream));
        ITable expectedTable = expectedDataSet.getTable("test_issue_tracker.IssueTypes");

        Assertion.assertEquals(expectedTable, filteredActualTable);

        actualTable = databaseDataSet.getTable("test_issue_tracker.IssueStates");
        filteredActualTable = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"id"});
        expectedTable = expectedDataSet.getTable("test_issue_tracker.IssueStates");

        Assertion.assertEquals(expectedTable, filteredActualTable);

        ITable issueStateToTransitionStates = databaseDataSet.getTable("test_issue_tracker.IssueStates_IssueStates");
        Assert.assertEquals(3,issueStateToTransitionStates.getRowCount());
        for(int i=1;i<2;i++) {
            Long sourceIssueState=((BigInteger)issueStateToTransitionStates.getValue(i,"IssueState_id")).longValue();
            Long toTransferIssueState=((BigInteger)issueStateToTransitionStates.getValue(i,"issueStatesToTransition_id")).longValue();
            assertEquals((long)sourceIssueState+1L,(long)toTransferIssueState);
        }
    }


    @Test
    public void remove() throws Exception {
        IssueType issueType = new IssueType();
        issueType.setId(1L);
        issueType.remove();

        IDataSet databaseDataSet = getConnection().createDataSet(new String[]{"test_issue_tracker.IssueTypes","test_issue_tracker.IssueStates"});
        ITable actualTable = databaseDataSet.getTable("test_issue_tracker.IssueTypes");

        InputStream expectedDataSetInputStream = getDataSetAsInputStream("testDataSet/issues/issueTypes/AfterDeleteIssueTypeExpectedDataset.xml");
        ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(expectedDataSetInputStream));
        expectedDataSet.addReplacementObject("[null]", null);
        ITable expectedTable = expectedDataSet.getTable("test_issue_tracker.IssueTypes");

        Assertion.assertEquals(expectedTable, actualTable);

        actualTable = databaseDataSet.getTable("test_issue_tracker.IssueStates");
        expectedTable = expectedDataSet.getTable("test_issue_tracker.IssueStates");

        Assertion.assertEquals(expectedTable,actualTable);
    }


}