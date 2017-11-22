package domain.issues;

import common.DBTestCase;
import common.beanFactory.BeanFactoryProvider;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.prefs.BackingStoreException;

import static org.junit.Assert.*;

/**
 * Created by Администратор on 31.10.2017.
 */
public class IssuesFinderTest extends DBTestCase{

    public IssuesFinderTest() {
        super("IssuesFinderTest");
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(getDataSetAsInputStream("testDataSet/issues/issues/IssuesFinderSetupDataset.xml"));
    }

    public IssuesFinder getFinder(){
        return (IssuesFinder) BeanFactoryProvider.getBeanFactory().getBean("issuesFinder");
    }

    @Test
    public void findIssuesByAuthor() throws Exception {
        List<Issue> issuesList = getFinder().findIssuesByAuthor(2L);
        assertEquals(2,issuesList.size());
        assertEquals(1L,(long)issuesList.get(0).getId());
        assertEquals(3L,(long)issuesList.get(1).getId());
        issuesList = getFinder().findIssuesByAuthor(3L);
        assertEquals(1,(long)issuesList.size());
        assertEquals(2L,(long)issuesList.get(0).getId());
    }

    @Test
    public void findIssuesByAssignee() throws Exception {
        List<Issue> issuesList = getFinder().findIssuesByAssignee(3L);
        assertEquals(2,issuesList.size());
        assertEquals(1L,(long)issuesList.get(0).getId());
        assertEquals(3L,(long)issuesList.get(1).getId());
        issuesList = getFinder().findIssuesByAssignee(2L);
        assertEquals(1,(long)issuesList.size());
        assertEquals(2L,(long)issuesList.get(0).getId());
    }

    @Test
    public void findIssuesInOneOfIssueStates() throws Exception {
        List<Issue> issuesList = getFinder().findIssuesInOneOfIssueStates(
                Arrays.asList(new IssueState(4L),new IssueState(5L))
        );
        assertEquals(2,issuesList.size());
        assertEquals(2L,(long)issuesList.get(0).getId());
        assertEquals(3L,(long)issuesList.get(1).getId());
    }

}