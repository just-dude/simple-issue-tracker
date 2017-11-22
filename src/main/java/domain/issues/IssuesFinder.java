package domain.issues;

import common.argumentAssert.Assert;
import dao.common.Dao;
import dao.issues.IssuesJpaDao;
import domain.common.SimpleFinder;
import domain.common.exception.DataAccessFailedBuisnessException;
import domain.issues.authorization.IssuesFinderCheckPermissionsStrategy;
import domain.issues.authorization.WithoutSoftDeletedIssuesFinderCheckPermissionsStrategy;
import domain.security.authorization.FinderCheckPermissionsStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Администратор on 31.10.2017.
 */
public class IssuesFinder extends SimpleFinder<Issue,Long> {

    public IssuesFinder(Dao dao) {
        super(dao,new WithoutSoftDeletedIssuesFinderCheckPermissionsStrategy());
    }

    public List<Issue> findIssuesByAuthor(Long authorId){
        Assert.notNull(authorId,"authorId");
        ((IssuesFinderCheckPermissionsStrategy)checkPermissionsStrategy).checkFindIssuesByAuthorPermission(authorId);
        try {
            return ((IssuesJpaDao) dao).findIssuesByAuthor(authorId);
        } catch (Exception e){
            throw new DataAccessFailedBuisnessException("Can't read data from data storage",e);
        }
    }

    public List<Issue> findIssuesByAssignee(Long assigneId){
        Assert.notNull(assigneId,"assigneId");
        ((IssuesFinderCheckPermissionsStrategy)checkPermissionsStrategy).checkFindIssuesByAssigneePermission(assigneId);
        try {
            return ((IssuesJpaDao) dao).findIssuesByAssignee(assigneId);
        } catch (Exception e){
            throw new DataAccessFailedBuisnessException("Can't read data from data storage",e);
        }
    }

    public List<Issue> findIssuesInOneOfIssueStates(List<IssueState> issueStates){
        Assert.notNull(issueStates,"issueStates");
        if(issueStates.size()==0){
            return new ArrayList<>();
        }
        ((IssuesFinderCheckPermissionsStrategy)checkPermissionsStrategy).checkFindIssuesInOneOfIssueSatatesPermission(issueStates);
        try {
            return ((IssuesJpaDao) dao).findIssuesInOneOfStates(issueStates);
        } catch (Exception e){
            throw new DataAccessFailedBuisnessException("Can't read data from data storage",e);
        }
    }
}
