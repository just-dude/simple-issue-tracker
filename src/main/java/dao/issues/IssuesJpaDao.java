package dao.issues;

import dao.common.SoftDeletedModedJpaDao;
import domain.issues.*;
import domain.issues.Issue;
import domain.users.UserAccount_;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Администратор on 31.10.2017.
 */
public class IssuesJpaDao extends SoftDeletedModedJpaDao<Issue,Long> {

    public IssuesJpaDao(EntityManager em) {
        super(em, Issue.class);
    }

    public IssuesJpaDao(EntityManager em, FindMode findMode) {
        super(em, Issue.class, findMode);
    }

    public List<Issue> findIssuesByAuthor(Long authorId){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Issue> cq = cb.createQuery(getEntityTypeClass());
        Root<Issue> root = cq.from(getEntityTypeClass());
        cq.select(root);
        List<Predicate> andPredicatesList = new ArrayList<>();
        applyFindAllMode(andPredicatesList,root,cb);
        andPredicatesList.add(cb.equal(root.get(Issue_.author).get("id"), authorId));
        cq.where(
                cb.and(andPredicatesList.toArray(new Predicate[andPredicatesList.size()]))
        );
        TypedQuery<Issue> typedQuery = em.createQuery(cq);
        return typedQuery.getResultList();
    }

    public List<Issue> findIssuesByAssignee(Long assigneeId){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Issue> cq = cb.createQuery(getEntityTypeClass());
        Root<Issue> root = cq.from(getEntityTypeClass());
        cq.select(root);
        List<Predicate> andPredicatesList = new ArrayList<>();
        applyFindAllMode(andPredicatesList,root,cb);
        andPredicatesList.add(cb.equal(root.get(Issue_.assignee).get("id"), assigneeId));
        cq.where(
                cb.and(andPredicatesList.toArray(new Predicate[andPredicatesList.size()]))
        );
        TypedQuery<Issue> typedQuery = em.createQuery(cq);
        return typedQuery.getResultList();
    }

    public List<Issue> findIssuesInOneOfStates(List<IssueState> issueStates){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Issue> cq = cb.createQuery(getEntityTypeClass());
        Root<Issue> root = cq.from(getEntityTypeClass());
        cq.select(root);
        List<Predicate> andPredicatesList = new ArrayList<>();
        applyFindAllMode(andPredicatesList,root,cb);
        List<Long> issueStatesIds = issueStates.stream().map(issueState -> issueState.getId()).collect(Collectors.toList());
        andPredicatesList.add(root.get(Issue_.state).get("id").in(issueStatesIds));
        cq.where(
                cb.and(andPredicatesList.toArray(new Predicate[andPredicatesList.size()]))
        );
        TypedQuery<Issue> typedQuery = em.createQuery(cq);
        return typedQuery.getResultList();
    }
}
