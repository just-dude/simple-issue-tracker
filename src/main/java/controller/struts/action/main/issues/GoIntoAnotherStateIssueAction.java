package controller.struts.action.main.issues;

import com.google.common.collect.Sets;
import common.beanFactory.BeanFactoryProvider;
import controller.struts.action.common.FormProviderHandlingExceptionsBaseAction;
import domain.common.Finder;
import domain.issues.Issue;
import domain.issues.IssueState;
import domain.issues.IssuesFinder;
import domain.users.UserAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

import static common.beanFactory.BeanFactoryProvider.getBeanFactory;

/**
 * Created by SuslovAI on 06.10.2017.
 */
public class GoIntoAnotherStateIssueAction extends FormProviderHandlingExceptionsBaseAction {

    protected static final Logger LOG = LogManager.getLogger(GoIntoAnotherStateIssueAction.class);

    private Long issueId;
    private Issue issue;
    private List<IssueState> issueStatesToTransition;
    private IssueState issueStateToTransition;

    @Override
    protected void doExecute() throws Exception {
        this.issue.goIntoState(issueStateToTransition);
    }

    @Override
    protected void doInput() throws Exception {
        IssuesFinder issuesFinder=getIssuesFinder();
        this.issue=issuesFinder.findOneWithInitPaths(issueId, Sets.newHashSet(Issue.STATES_TO_TRANSITION_OF_ISSUE_STATE_INIT_PATH));
        this.issueStatesToTransition=issue.getState().getIssueStatesToTransition();
    }

    protected IssuesFinder getIssuesFinder(){
        return (IssuesFinder) BeanFactoryProvider.getBeanFactory().getBean("issuesFinder");
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }

    public List<IssueState> getIssueStatesToTransition() {
        return issueStatesToTransition;
    }

    public void setIssueStatesToTransition(List<IssueState> issueStatesToTransition) {
        this.issueStatesToTransition = issueStatesToTransition;
    }

    public IssueState getIssueStateToTransition() {
        return issueStateToTransition;
    }

    public void setIssueStateToTransition(IssueState issueStateToTransition) {
        this.issueStateToTransition = issueStateToTransition;
    }

    @Override
    protected Logger getLogger() {
        return LOG;
    }
}
