package domain.issues.authorization;

import domain.common.exception.AuthorizationFailedException;
import domain.issues.Issue;
import domain.issues.IssueState;
import domain.security.SecuritySubjectUtils;
import domain.security.authorization.PermissionStringConstants;
import domain.security.authorization.WithoutSoftDeletedFinderCheckPermissionsStrategy;

import java.util.List;

/**
 * Created by Администратор on 31.10.2017.
 */
public class WithoutSoftDeletedIssuesFinderCheckPermissionsStrategy
        extends WithoutSoftDeletedFinderCheckPermissionsStrategy<Issue>
        implements IssuesFinderCheckPermissionsStrategy {

    public WithoutSoftDeletedIssuesFinderCheckPermissionsStrategy() {
        super(IssuesPermissionStringConstants.INSTANCE);
    }

    @Override
    public void checkFindIssuesByAuthorPermission(Long authorId) throws AuthorizationFailedException {
        SecuritySubjectUtils.checkPermission(((IssuesPermissionStringConstants)permissionStringConsts).getFindAllByAuthorPermission(authorId));
    }

    @Override
    public void checkFindIssuesByAssigneePermission(Long assigneeId) throws AuthorizationFailedException {
        SecuritySubjectUtils.checkPermission(
                ((IssuesPermissionStringConstants)permissionStringConsts).getFindAllByAssigneePermission(assigneeId)
        );
    }

    @Override
    public void checkFindIssuesInOneOfIssueSatatesPermission(List<IssueState> issueStates) throws AuthorizationFailedException {
    }
}
