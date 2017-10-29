package domain.issues;

import common.argumentAssert.Assert;
import dao.issues.IssueStatesJpaDao;
import domain.common.SimpleFinder;
import domain.common.exception.BusinessException;
import domain.common.exception.DataAccessFailedBuisnessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SuslovAI on 23.10.2017.
 */
public class IssueStatesFinder extends SimpleFinder<IssueState, Long> {

    public IssueStatesFinder(IssueStatesJpaDao dao) {
        super(dao);
    }

    public List<IssueState> findAllByIssueType(Long issueTypeId) {
        try {
            Assert.notNull(issueTypeId,"issueTypeId");
            return ((IssueStatesJpaDao) dao).findAllByIssueType(issueTypeId);
        } catch (Exception e) {
            throw new DataAccessFailedBuisnessException("An error has occured on read from data storage", e, this.getClass().getSimpleName() + ".findAllByIssueType(Long issueTypeId)", issueTypeId);
        }
    }

    public IssueState findInitialIssueStateByIssueType(Long issueTypeId){
        try {
            Assert.notNull(issueTypeId,"issueTypeId");
            return ((IssueStatesJpaDao) dao).findInitialIssueStateByIssueType(issueTypeId);
        } catch (Exception e) {
            throw new DataAccessFailedBuisnessException("An error has occured on read from data storage", e, this.getClass().getSimpleName() + ".findInitialIssueStateByIssueType(Long issueTypeId)", issueTypeId);
        }
    }

}
