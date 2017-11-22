package controller.struts.action.main.issues;

import common.beanFactory.BeanFactoryProvider;
import controller.struts.action.common.FormProviderHandlingExceptionsBaseAction;
import domain.issues.Issue;
import domain.issues.IssueState;
import domain.issues.IssuesFinder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by SuslovAI on 06.10.2017.
 */
public class ChangeIssueRequiredResolvedDateTimeAction extends FormProviderHandlingExceptionsBaseAction {

    protected static final Logger LOG = LogManager.getLogger(ChangeIssueRequiredResolvedDateTimeAction.class);

    private Long issueId;
    private Issue issue;
    private LocalDateTime requiredResolvedDateTime;

    @Override
    protected void doExecute() throws Exception {
        this.issue.changeRequiredResolvedDateTime(ZonedDateTime.ofLocal(requiredResolvedDateTime,getClock().getZone(),null));
    }

    @Override
    protected void doInput() throws Exception {
        IssuesFinder issuesFinder=getIssuesFinder();
        this.issue=issuesFinder.findOne(issueId);
        this.requiredResolvedDateTime=issue.getRequiredResolvedDateTime().withZoneSameLocal(getClock().getZone()).toLocalDateTime();
    }

    protected IssuesFinder getIssuesFinder(){
        return (IssuesFinder) BeanFactoryProvider.getBeanFactory().getBean("issuesFinder");
    }

    protected Clock getClock(){
        return (Clock) BeanFactoryProvider.getBeanFactory().getBean("clock");
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

    public LocalDateTime getRequiredResolvedDateTime() {
        return requiredResolvedDateTime;
    }

    public void setRequiredResolvedDateTime(LocalDateTime requiredResolvedDateTime) {
        this.requiredResolvedDateTime = requiredResolvedDateTime;
    }

    @Override
    protected Logger getLogger() {
        return LOG;
    }
}
