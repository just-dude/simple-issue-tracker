package domain.issues;

import common.DBTestCase;
import common.beanFactory.BeanFactoryProvider;
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

public class IssueStateTest extends DBTestCase {

    public IssueStateTest() {
        super("IssueStateTest");
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(getDataSetAsInputStream("testDataSet/issues/issueStates/IssueStatesSetupDataset.xml"));
    }

    @Test
    public void isValidAndGetConstraintsViolations() throws Exception {
        IssueState validIssueState = new IssueState(null,"newInitialState",
                true,false,null,new IssueType(1L,null,null));
        assertTrue(validIssueState.isValid());
        try {
            validIssueState.getConstraintsViolations();
            fail();
        } catch (ConstraintValidationException e) {
        }

        IssueState invalidIssueState = new IssueState(null,"newInitialState.",
                true,false,null,null);
        assertFalse(invalidIssueState.isValid());
        System.out.println(invalidIssueState.getConstraintsViolations());
        assertEquals(2, invalidIssueState.getConstraintsViolations().size());
    }

    @Test
    public void testInsert() throws Exception {
        List<IssueState> issueStatesList = new ArrayList<IssueState>();
        issueStatesList.add(
                new IssueState(null, "newInitialState", true, false, null,
                        new IssueType(2L, null, null)
                ));
        issueStatesList.add(
                new IssueState(null, "newFirstTransitionState", false, false, null,
                        new IssueType(2L, null, null)
                ));
        issueStatesList.add(
                new IssueState(null, "newSecondTransitionState", false, false, null,
                        new IssueType(2L, null, null)
                ));
        issueStatesList.add(
                new IssueState(null, "newFinishState", false, true, null,
                        new IssueType(2L, null, null)
                ));
        Map<Integer, List<Integer>> issueStateIndexToTransferIssueStateIndexesListMap = new HashMap<>();
        issueStateIndexToTransferIssueStateIndexesListMap.put(0, Arrays.asList(1));
        issueStateIndexToTransferIssueStateIndexesListMap.put(1, Arrays.asList(2));
        issueStateIndexToTransferIssueStateIndexesListMap.put(2, Arrays.asList(3));

        new IssueType(2L,null,null).saveIssueStatesAndTransitions(
                issueStatesList,issueStateIndexToTransferIssueStateIndexesListMap
        );

        IDataSet databaseDataSet = getConnection().createDataSet(
                new String[]{"test_issue_tracker.IssueStates","test_issue_tracker.IssueStates_IssueStates"});
        ITable actualTable = databaseDataSet.getTable("test_issue_tracker.IssueStates");
        ITable filteredActualTable = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"id"});
        InputStream expectedDataSetInputStream = getDataSetAsInputStream("testDataSet/issues/issueStates/AfterInsertIssueStatesExpectedDataset.xml");
        ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(expectedDataSetInputStream));
        ITable expectedTable = expectedDataSet.getTable("test_issue_tracker.IssueStates");

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
        IssueStatesFinder issueStatesFinder = (IssueStatesFinder) BeanFactoryProvider.getBeanFactory().getBean("issueStatesFinder");
        List<IssueState> issueStateList = issueStatesFinder.findAllByIssueType(1l);
        assertEquals(3,issueStateList.size());
    }


    @Test
    public void updateIssueStates() throws Exception {
        List<IssueState> issueStatesList = new ArrayList<IssueState>();
        issueStatesList.add(
                new IssueState(null, "newInitialState", true, false, null,
                        null
                ));
        issueStatesList.add(
                new IssueState(null, "newFirstTransitionState", false, false, null,
                        null
                ));
        issueStatesList.add(
                new IssueState(null, "newSecondTransitionState", false, false, null,
                        null
                ));
        issueStatesList.add(
                new IssueState(null, "newFinishState", false, true, null,null
                ));
        Map<Integer, List<Integer>> issueStateIndexToTransferIssueStateIndexesListMap = new HashMap<>();
        issueStateIndexToTransferIssueStateIndexesListMap.put(0, Arrays.asList(1));
        issueStateIndexToTransferIssueStateIndexesListMap.put(1, Arrays.asList(2));
        issueStateIndexToTransferIssueStateIndexesListMap.put(2, Arrays.asList(3));

        new IssueType(1L,null,null).saveIssueStatesAndTransitions(
                issueStatesList,issueStateIndexToTransferIssueStateIndexesListMap
        );

        IDataSet databaseDataSet = getConnection().createDataSet(
                new String[]{"test_issue_tracker.IssueStates","test_issue_tracker.IssueStates_IssueStates"});
        ITable actualTable = databaseDataSet.getTable("test_issue_tracker.IssueStates");
        ITable filteredActualTable = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"id"});
        InputStream expectedDataSetInputStream = getDataSetAsInputStream("testDataSet/issues/issueStates/AfterUpdateIssueStatesExpectedDataset.xml");
        ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(expectedDataSetInputStream));
        ITable expectedTable = expectedDataSet.getTable("test_issue_tracker.IssueStates");

        Assertion.assertEquals(expectedTable, filteredActualTable);

        ITable issueStateTransferStates = databaseDataSet.getTable("test_issue_tracker.IssueStates_IssueStates");
        Assert.assertEquals(3,issueStateTransferStates.getRowCount());
        for(int i=0;i<3;i++) {
            Long sourceIssueState=((BigInteger)issueStateTransferStates.getValue(i,"IssueState_id")).longValue();
            Long toTransferIssueState=((BigInteger)issueStateTransferStates.getValue(i,"issueStatesToTransition_id")).longValue();
            assertEquals((long)sourceIssueState+1L,(long)toTransferIssueState);
        }
    }


    @Test
    public void deleteIssueState() throws Exception {
        IssueState issueState = new IssueState();
        issueState.setId(2L);
        issueState.remove();

        IDataSet databaseDataSet = getConnection().createDataSet(new String[]{"test_issue_tracker.IssueStates","test_issue_tracker.IssueStates_IssueStates"});
        ITable actualTable = databaseDataSet.getTable("test_issue_tracker.IssueStates");

        InputStream expectedDataSetInputStream = getDataSetAsInputStream("testDataSet/issues/issueStates/AfterDeleteIssueStateExpectedDataset.xml");
        ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(expectedDataSetInputStream));
        expectedDataSet.addReplacementObject("[null]", null);
        ITable expectedTable = expectedDataSet.getTable("test_issue_tracker.IssueStates");

        Assertion.assertEquals(expectedTable, actualTable);
        Assert.assertEquals(0,databaseDataSet.getTable("test_issue_tracker.IssueStates_IssueStates").getRowCount());
    }

    @Test
    public void deleteIssueTypeWithIssueState() throws Exception {
        IssueType issueType = new IssueType();
        issueType.setId(1L);
        issueType.remove();

        IDataSet databaseDataSet = getConnection().createDataSet(new String[]{"test_issue_tracker.IssueTypes","test_issue_tracker.IssueStates","test_issue_tracker.IssueStates_IssueStates"});
        ITable actualTable = databaseDataSet.getTable("test_issue_tracker.IssueTypes");

        InputStream expectedDataSetInputStream = getDataSetAsInputStream("testDataSet/issues/issueStates/AfterDeleteTypeIssueWithIssueStatesExpectedDataset.xml");
        ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(expectedDataSetInputStream));
        expectedDataSet.addReplacementObject("[null]", null);
        ITable expectedTable = expectedDataSet.getTable("test_issue_tracker.IssueTypes");

        Assertion.assertEquals(expectedTable, actualTable);
        Assert.assertEquals(0,databaseDataSet.getTable("test_issue_tracker.IssueStates_IssueStates").getRowCount());
        Assert.assertEquals(0,databaseDataSet.getTable("test_issue_tracker.IssueStates").getRowCount());
    }

    @Test
    public void testFindInitialIssueStateByIssueType(){
        IssueStatesFinder issueStatesFinder = (IssueStatesFinder)BeanFactoryProvider.getBeanFactory().getBean("issueStatesFinder");
        IssueState initialIssueState=issueStatesFinder.findInitialIssueStateByIssueType(1L);
        assertTrue(initialIssueState.isInitialState());
        assertFalse(initialIssueState.isFinishState());
        assertEquals(1L,(long)initialIssueState.getId());
    }

    @Test
    public void testFindIssueStateToTransitionFromInitialState(){
        IssueStatesFinder issueStatesFinder = (IssueStatesFinder)BeanFactoryProvider.getBeanFactory().getBean("issueStatesFinder");
        IssueState initialIssueState=issueStatesFinder.findInitialIssueStateByIssueType(1L);
        List<IssueState> issueStatesToTransitionList=initialIssueState.getIssueStatesToTransition();
        assertEquals(1,issueStatesToTransitionList.size());
        assertFalse(issueStatesToTransitionList.get(0).isInitialState());
        assertFalse(issueStatesToTransitionList.get(0).isFinishState());
        assertEquals(2L,(long)issueStatesToTransitionList.get(0).getId());
    }
}