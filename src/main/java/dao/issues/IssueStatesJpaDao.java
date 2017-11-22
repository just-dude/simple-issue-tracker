package dao.issues;

import dao.common.SoftDeletedModedJpaDao;
import domain.issues.Issue;
import domain.issues.IssueState;
import domain.issues.IssueState_;
import domain.issues.IssueType_;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SuslovAI on 23.10.2017.
 */
public class IssueStatesJpaDao extends SoftDeletedModedJpaDao<IssueState, Long> {

    public IssueStatesJpaDao(EntityManager em) {
        super(em, IssueState.class);
    }

    public IssueStatesJpaDao(EntityManager em, FindMode findMode) {
        super(em, IssueState.class, findMode);
    }

    public List<IssueState> findAllByIssueType(Long issueTypeId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<IssueState> cq = cb.createQuery(getEntityTypeClass());
        Root<IssueState> root = cq.from(getEntityTypeClass());
        cq.select(root);
        List<Predicate> andPredicatesList = new ArrayList<>();
        applyFindAllMode(andPredicatesList,root,cb);
        andPredicatesList.add(cb.equal(root.get(IssueState_.issueType).get(IssueType_.id), issueTypeId));
        cq.where(
                cb.and(andPredicatesList.toArray(new Predicate[andPredicatesList.size()]))
        );
        TypedQuery<IssueState> typedQuery = em.createQuery(cq);
        return typedQuery.getResultList();
    }

    public IssueState findInitialIssueStateByIssueType(Long issueTypeId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<IssueState> cq = cb.createQuery(getEntityTypeClass());
        Root<IssueState> root = cq.from(getEntityTypeClass());
        cq.select(root);
        List<Predicate> andPredicatesList = new ArrayList<>();
        applyFindAllMode(andPredicatesList,root,cb);
        andPredicatesList.add(cb.equal(root.get(IssueState_.issueType).get(IssueType_.id), issueTypeId));
        andPredicatesList.add(cb.equal(root.get("isInitialState"), true));
        cq.where(
                cb.and(andPredicatesList.toArray(new Predicate[andPredicatesList.size()]))
        );
        TypedQuery<IssueState> typedQuery = em.createQuery(cq);
        List<IssueState> issueStateList = typedQuery.getResultList();
        return (issueStateList.size()>0)?issueStateList.get(0):null;
    }
}
