package domain.issues;

import com.google.common.collect.Sets;
import common.DBTestCase;
import common.beanFactory.BeanFactoryProvider;
import domain.common.Finder;
import domain.common.exception.AuthorizationFailedException;
import domain.common.exception.BusinessRuleViolationException;
import domain.common.exception.ValidationFailedException;
import domain.security.SecuritySubjectUtils;
import domain.users.UserAccount;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Before;
import org.junit.Test;
import smartvalidation.exception.ConstraintValidationException;

import java.io.InputStream;
import java.time.Clock;
import java.time.ZonedDateTime;

public class IssueTest extends DBTestCase {

    public IssueTest() {
        super("IssueTest");
    }

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(getDataSetAsInputStream("testDataSet/issues/issues/IssuesSetupDataset.xml"));
    }

    @Test
    public void testIsValidAndGetConstraintsViolations() throws Exception {
        Issue validIssue = new Issue("New Issue","some new description 1",new IssueType(1L),
                new UserAccount(2L),Issue.Priority.Low,ZonedDateTime.now().plusDays(2),null);
        assertTrue(validIssue.isValid());
        try {
            validIssue.getConstraintsViolations();
            fail();
        } catch (ConstraintValidationException e) {
        }
        Issue invalidIssue = new Issue();
        assertFalse(invalidIssue.isValid());
        System.out.println(invalidIssue.getConstraintsViolations());
        assertEquals(5, invalidIssue.getConstraintsViolations().size());
    }

    @Test
    public void testInsert() throws Exception {
        SecuritySubjectUtils.login("secondCommonUser", "234");

        Issue issue = new Issue("New Issue","some new description 1",new IssueType(2L),
                new UserAccount(2L),Issue.Priority.Low,ZonedDateTime.now().plusDays(2),new Issue(2L));
        Issue savedIssue = issue.save();
        assertNotNull(savedIssue.getId());

        IDataSet databaseDataSet = getConnection().createDataSet(new String[]{"test_issue_tracker.Issues"});
        ITable actualTable = databaseDataSet.getTable("test_issue_tracker.Issues");
        ITable filteredActualTable = DefaultColumnFilter.excludedColumnsTable(actualTable,new String[]{
                "id","additionalAttributes","requiredResolvedDateTime","resolvedDateTime","createdDateTime"
        });
        InputStream expectedDataSetInputStream = getDataSetAsInputStream("testDataSet/issues/issues/AfterInsertIssueExpectedDataset.xml");
        ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(expectedDataSetInputStream));
        expectedDataSet.addReplacementObject("[null]",null);
        ITable expectedTable = expectedDataSet.getTable("test_issue_tracker.Issues");

        Assertion.assertEquals(expectedTable, filteredActualTable);
    }

    @Test
    public void testCatchBusinessRuleViolationExceptionOnChangeAndSaveState() throws Exception {
        SecuritySubjectUtils.login("secondCommonUser", "234");

        Finder<Issue,Long> issuesFinder = (Finder<Issue,Long>)BeanFactoryProvider.getBeanFactory().getBean("issuesFinder");
        Issue issue = issuesFinder.findOne(1L);
        issue.setPriority(Issue.Priority.Low);
        try {
            issue.save();
            fail("BusinessRuleViolationException expected. Can't change(resave) exists issue");
        } catch(BusinessRuleViolationException e){
        }
    }

    @Test
    public void testChangeState() throws Exception {
        SecuritySubjectUtils.login("secondCommonUser", "234");

        Finder<Issue,Long> issuesFinder = (Finder<Issue,Long>)BeanFactoryProvider.getBeanFactory().getBean("issuesFinder");
        Issue issue = issuesFinder.findOneWithInitPaths(1L, Sets.newHashSet(Issue.STATES_TO_TRANSITION_OF_ISSUE_STATE_INIT_PATH));
        issue.goIntoState(issue.getState().getIssueStatesToTransition().get(0));

        IDataSet databaseDataSet = getConnection().createDataSet(new String[]{"test_issue_tracker.Issues"});
        ITable actualTable = databaseDataSet.getTable("test_issue_tracker.Issues");
        ITable filteredActualTable = DefaultColumnFilter.excludedColumnsTable(actualTable,new String[]{
                "id","additionalAttributes","requiredResolvedDateTime","resolvedDateTime","createdDateTime","parentId"
        });
        InputStream expectedDataSetInputStream = getDataSetAsInputStream("testDataSet/issues/issues/AfterChangeIssueStateExpectedDataset.xml");
        ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(expectedDataSetInputStream));
        expectedDataSet.addReplacementObject("[null]", null);
        ITable expectedTable = expectedDataSet.getTable("test_issue_tracker.Issues");

        Assertion.assertEquals(expectedTable, filteredActualTable);
    }

    @Test
    public void testChangeStateByUserThatHasNotAccess(){
        SecuritySubjectUtils.login("firstCommonUser", "234");

        Finder<Issue,Long> issuesFinder = (Finder<Issue,Long>)BeanFactoryProvider.getBeanFactory().getBean("issuesFinder");
        Issue issue = issuesFinder.findOneWithInitPaths(1L,Sets.newHashSet(Issue.STATES_TO_TRANSITION_OF_ISSUE_STATE_INIT_PATH));
        try {
            issue.goIntoState(issue.getState().getIssueStatesToTransition().get(0));
            fail("User with id=2 can't change state of this issue");
        } catch (AuthorizationFailedException e){
        }
    }


    @Test
    public void testChangeRequiredResolvedDateTime() throws Exception {
        SecuritySubjectUtils.login("firstCommonUser", "234");


        Issue issue = new Issue("New Issue","some new description 1",new IssueType(2L),
                new UserAccount(2L),Issue.Priority.Low,ZonedDateTime.now(getClock()).plusDays(2),null);
        Issue savedIssue = issue.save();
        ZonedDateTime newRequiredResolvedDateTime =ZonedDateTime.now(getClock()).plusDays(3);
        savedIssue.changeRequiredResolvedDateTime(newRequiredResolvedDateTime);

        Finder<Issue,Long> issuesFinder = (Finder<Issue,Long>)BeanFactoryProvider.getBeanFactory().getBean("issuesFinder");
        Issue issueInDataStorage = issuesFinder.findOne(savedIssue.getId());
        assertTrue(newRequiredResolvedDateTime.isEqual(issueInDataStorage.getRequiredResolvedDateTime()));

    }

    @Test
    public void testChangeRequiredResolvedDateTimeByUserThatHasNoAccess() throws Exception {
        SecuritySubjectUtils.login("secondCommonUser", "234");

        Issue issue = new Issue("New Issue","some new description 1",new IssueType(2L),
                new UserAccount(2L),Issue.Priority.Low,ZonedDateTime.now(getClock()).plusDays(2),null);

        Issue savedIssue = issue.save();

        SecuritySubjectUtils.logout();
        SecuritySubjectUtils.login("firstCommonUser", "234");
        ZonedDateTime newRequiredResolvedDateTime =ZonedDateTime.now(getClock()).plusDays(3);

        try {
            savedIssue.changeRequiredResolvedDateTime(newRequiredResolvedDateTime);
            fail("That user must have no access to change required resolved datetime");
        } catch (AuthorizationFailedException e){
        }
    }

    @Test
    public void testChangeRequiredResolvedDateTimeWithDateTimeLessThanCurrent() throws Exception {
        SecuritySubjectUtils.login("firstCommonUser", "234");


        Issue issue = new Issue("New Issue","some new description 1",new IssueType(2L),
                new UserAccount(2L),Issue.Priority.Low,ZonedDateTime.now(getClock()).plusDays(2),null);
        Issue savedIssue = issue.save();
        ZonedDateTime newRequiredResolvedDateTime =ZonedDateTime.now(getClock()).plusDays(1);
        try {
            savedIssue.changeRequiredResolvedDateTime(newRequiredResolvedDateTime);
            fail("ValidationFailedException excpected because new date must be greater than current");
        } catch(ValidationFailedException e){
        }
    }

    @Test
    public void testChangeRequiredResolvedDateTimeWhenCurrentDateTimeIsNull() throws Exception {
        SecuritySubjectUtils.login("firstCommonUser", "234");


        Issue issue = new Issue("New Issue","some new description 1",new IssueType(2L),
                new UserAccount(2L),Issue.Priority.Low,null,null);
        Issue savedIssue = issue.save();
        ZonedDateTime newRequiredResolvedDateTime =ZonedDateTime.now(getClock()).plusDays(1);
        try {
            savedIssue.changeRequiredResolvedDateTime(newRequiredResolvedDateTime);
            fail("BusinessRuleViolationException excpected because issue haven't RequiredResolvedDateTime");
        } catch(BusinessRuleViolationException e){
        }
    }

    @Test
    public void testRemove() throws Exception {
        SecuritySubjectUtils.login("admin", "123");

        Issue issue = new Issue(1L);
        issue.remove();

        IDataSet databaseDataSet = getConnection().createDataSet(new String[]{"test_issue_tracker.Issues"});
        ITable actualTable = databaseDataSet.getTable("test_issue_tracker.Issues");
        ITable filteredActualTable = DefaultColumnFilter.excludedColumnsTable(actualTable,new String[]{
                "id","additionalAttributes","requiredResolvedDateTime","resolvedDateTime","createdDateTime","parentId"
        });
        InputStream expectedDataSetInputStream = getDataSetAsInputStream("testDataSet/issues/issues/AfterSoftDeleteIssueExpectedDataset.xml");
        ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(expectedDataSetInputStream));
        ITable expectedTable = expectedDataSet.getTable("test_issue_tracker.Issues");

        Assertion.assertEquals(expectedTable, filteredActualTable);
    }

    @Test
    public void testGetSubIssuesList(){
        Finder<Issue,Long> issuesFinder = (Finder<Issue,Long>)BeanFactoryProvider.getBeanFactory().getBean("issuesFinder");
        Issue issue = issuesFinder.findOneWithInitPaths(1L,Sets.newHashSet(Issue.ALL_SUBISSUES_INIT_PATH));
        assertEquals(1,issue.getSubIssues().size());
        assertEquals(2L,(long)issue.getSubIssues().get(0).getId());
        issue=issue.getSubIssues().get(0);
        assertEquals(1,issue.getSubIssues().size());
        assertEquals(3L,(long)issue.getSubIssues().get(0).getId());
        issue=issue.getSubIssues().get(0);
        assertEquals(0,issue.getSubIssues().size());
    }

    private Clock getClock(){
        return (Clock)BeanFactoryProvider.getBeanFactory().getBean("clock");
    }
}