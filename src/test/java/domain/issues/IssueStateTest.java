package domain.issues;

import com.google.common.collect.Sets;
import common.DBTestCase;
import common.beanFactory.BeanFactoryProvider;
import domain.common.Finder;
import org.apache.struts2.components.Bean;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;
import smartvalidation.exception.ConstraintValidationException;

import java.io.InputStream;
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
    public void deleteIssueState() throws Exception {
        IssueState issueState = new IssueState();
        issueState.setId(2L);
        issueState.remove();

        IDataSet databaseDataSet = getConnection().createDataSet(new String[]{"test_issue_tracker.IssueStates"});
        ITable actualTable = databaseDataSet.getTable("test_issue_tracker.IssueStates");

        InputStream expectedDataSetInputStream = getDataSetAsInputStream("testDataSet/issues/issueStates/AfterDeleteIssueStateExpectedDataset.xml");
        ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(expectedDataSetInputStream));
        expectedDataSet.addReplacementObject("[null]", null);
        ITable expectedTable = expectedDataSet.getTable("test_issue_tracker.IssueStates");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testFindInitialIssueStateByIssueType(){
        IssueState initialIssueState=new IssueType(1L).getInitialIssueState();
        assertTrue(initialIssueState.isInitialState());
        assertFalse(initialIssueState.isFinishState());
        assertEquals(1L,(long)initialIssueState.getId());
    }

    @Test
    public void testFindIssueStateToTransitionFromInitialState(){
        Finder<IssueState,Long> issueStateFinder= (Finder<IssueState,Long>) BeanFactoryProvider.getBeanFactory().getBean("issueStatesFinder");
        IssueState issueState=issueStateFinder.findOneWithInitPaths(1L, Sets.newHashSet(IssueState.ISSUE_STATES_TO_TRANSITION_INIT_PATH));
        List<IssueState> issueStatesToTransitionList= issueState.getIssueStatesToTransition();
        assertEquals(1,issueStatesToTransitionList.size());
        assertFalse(issueStatesToTransitionList.get(0).isInitialState());
        assertFalse(issueStatesToTransitionList.get(0).isFinishState());
        assertEquals(2L,(long)issueStatesToTransitionList.get(0).getId());
    }
}