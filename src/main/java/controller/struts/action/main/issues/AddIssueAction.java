package controller.struts.action.main.issues;

import controller.struts.action.common.FormProviderHandlingExceptionsBaseAction;
import domain.common.Finder;
import domain.issues.Issue;
import domain.users.UserAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

import static common.beanFactory.BeanFactoryProvider.getBeanFactory;

/**
 * Created by SuslovAI on 06.10.2017.
 */
public class AddIssueAction extends FormProviderHandlingExceptionsBaseAction {

    protected static final Logger LOG = LogManager.getLogger(AddIssueAction.class);

    private Issue issue = new Issue();

    @Override
    protected void doExecute() throws Exception {
        issue.save();
        issue = new Issue();
    }

    @Override
    protected void doInput() throws Exception {
        issue = new Issue(null, "issue name", "description", "some: attribute\nsome2: attribute", null, Issue.Priority.Low,
                null, new UserAccount(3L, null, null, null), null, null, null,
                ZonedDateTime.now(ZoneId.of("Asia/Yakutsk")), null);
    }

    public Map<String, String> findAllUserAccounts() {
        Map<String, String> userAccountIdWithNamesMap = null;
        try {
            Finder<UserAccount, Long> userAccountsFinder = (Finder<UserAccount, Long>) getBeanFactory().getBean("userAccountsFinder");
            List<UserAccount> allUserAccounts = userAccountsFinder.findAll();
            userAccountIdWithNamesMap = new LinkedHashMap(allUserAccounts.size());
            Collections.sort(allUserAccounts, (UserAccount ug1, UserAccount ug2) -> {
                return ug1.getProfile().getFullName().compareTo(ug2.getProfile().getFullName());
            });
            for (UserAccount userAccount : allUserAccounts) {
                userAccountIdWithNamesMap.put(userAccount.getId().toString(), userAccount.getProfile().getFullName());
            }
        } catch (Exception e) {
            userAccountIdWithNamesMap = new LinkedHashMap(0);
            LOG.debug("Exception has occured on AddIssueAction.findAllUserAccounts", e);
        }
        return userAccountIdWithNamesMap;
    }


    public Map<String, String> findAllIssueTypes() {
        return getEntitiesIdWithNamesMapByFinderName("issueTypesFinder");
    }

    public Map<String, String> findAllIssuePriorities() {
        HashMap<String, String> result = new HashMap<>();
        for (Issue.Priority priority : Issue.Priority.values()) {
            result.put(priority.name(), priority.name());
        }
        return result;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    @Override
    protected Logger getLogger() {
        return LOG;
    }
}
