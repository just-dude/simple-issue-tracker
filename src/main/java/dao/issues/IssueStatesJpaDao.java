package dao.issues;

import dao.common.JpaDao;
import dao.common.exception.DaoException;
import domain.issues.IssueState;
import domain.issues.IssueState_;
import domain.issues.IssueType_;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by SuslovAI on 23.10.2017.
 */
public class IssueStatesJpaDao extends JpaDao<IssueState, Long> {

    public IssueStatesJpaDao(EntityManager em) {
        super(em, IssueState.class);
    }

    public List<IssueState> findAllByIssueType(Long issueTypeId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<IssueState> cq = cb.createQuery(getEntityTypeClass());
        Root<IssueState> root = cq.from(getEntityTypeClass());
        cq.select(root);
        cq.where(cb.equal(root.get(IssueState_.issueType).get(IssueType_.id), issueTypeId));
        TypedQuery<IssueState> typedQuery = em.createQuery(cq);
        return typedQuery.getResultList();
    }

    public IssueState findInitialIssueStateByIssueType(Long issueTypeId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<IssueState> cq = cb.createQuery(getEntityTypeClass());
        Root<IssueState> root = cq.from(getEntityTypeClass());
        cq.select(root);
        cq.where(
                cb.and(
                        cb.equal(root.get(IssueState_.issueType).get(IssueType_.id), issueTypeId),
                        cb.equal(root.get("isInitialState"), true)
                )
        );
        TypedQuery<IssueState> typedQuery = em.createQuery(cq);
        return typedQuery.getSingleResult();
    }
}
