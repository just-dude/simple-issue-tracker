package domain.issues.authorization;

import domain.common.exception.AuthorizationFailedException;
import domain.issues.Issue;
import domain.issues.IssueState;
import domain.security.authorization.FinderCheckPermissionsStrategy;

import java.util.List;

/**
 * Created by Администратор on 31.10.2017.
 */
public interface IssuesFinderCheckPermissionsStrategy extends FinderCheckPermissionsStrategy<Issue> {
    void checkFindIssuesByAuthorPermission(Long authorId) throws AuthorizationFailedException;

    void checkFindIssuesByAssigneePermission(Long assigneeId) throws AuthorizationFailedException;

    void checkFindIssuesInOneOfIssueSatatesPermission(List<IssueState> issueStates) throws AuthorizationFailedException;
}
