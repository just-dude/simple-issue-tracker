package dao.common;


import domain.common.HavingSoftDeletedDomainObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.*;

public class SoftDeletedModedJpaDao<T extends HavingSoftDeletedDomainObject, ID extends Serializable> extends SimpleJpaDao<T, ID> {

    public enum FindMode {
        WithSoftDeleted, WithoutSoftDeleted, OnlySoftDeleted
    }

    protected static final String IS_SOFT_DELETED_ATTRIBUTE_NAME="isSoftDeleted";
    protected FindMode findMode;

    public SoftDeletedModedJpaDao(EntityManager em, Class<T> entityClass, FindMode findMode) {
        super(em, entityClass);
        this.findMode = findMode;
    }

    protected void applyFindAllMode(List<Predicate> andPredicateList,Root root,CriteriaBuilder cb){
        if(!findMode.equals(FindMode.WithSoftDeleted)) {
            if(findMode.equals(FindMode.OnlySoftDeleted)) {
                andPredicateList.add(
                        cb.equal(root.get(IS_SOFT_DELETED_ATTRIBUTE_NAME),true )
                );
            } else{
                andPredicateList.add(
                        cb.or(cb.equal(root.get(IS_SOFT_DELETED_ATTRIBUTE_NAME),false ),cb.isNull(root.get(IS_SOFT_DELETED_ATTRIBUTE_NAME)))
                );
            }
        }
    }

    public SoftDeletedModedJpaDao(EntityManager em, Class<T> entityClass) {
        super(em, entityClass);
        this.findMode = FindMode.WithSoftDeleted;
    }

    @Override
    public T findOne(ID id) {
        T result=super.findOne(id);
        if(result!=null) {
            return (FindMode.WithoutSoftDeleted.equals(findMode) && result.isSoftDeleted()) ? null : result;
        } else{
            return null;
        }
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getEntityTypeClass());
        Root<T> root = cq.from(getEntityTypeClass());
        cq.select(root);
        List<Predicate> andPredicatesList = new ArrayList<>();
        applyFindAllMode(andPredicatesList,root,cb);
        if(andPredicatesList.size()>0) {
            cq.where(andPredicatesList.get(0));
        }
        TypedQuery<T> typedQuery = em.createQuery(cq);
        return typedQuery.getResultList();
    }

    @Override
    public List<T> findAll(Sort sort) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getEntityTypeClass());
        Root<T> root = cq.from(getEntityTypeClass());
        cq.select(root);
        List<Predicate> andPredicatesList = new ArrayList<>();
        applyFindAllMode(andPredicatesList,root,cb);
        if(andPredicatesList.size()>0) {
            cq.where(andPredicatesList.get(0));
        }
        sort.forEach(order -> {
            if (order.isAscending()) {
                cq.orderBy(cb.asc(root.get(order.getProperty())));
            } else {
                cq.orderBy(cb.desc(root.get(order.getProperty())));
            }
        });
        TypedQuery<T> typedQuery = em.createQuery(cq);
        return typedQuery.getResultList();
    }

    @Override
    public List<T> findAll(Iterable<ID> iterable) {
        if (!(iterable instanceof Collection)) {
            throw new IllegalArgumentException("Argument 'iterable' in 'SimpleJpaDao.findAll(Iterable<ID> iterable)' must be instanceof Collection");
        }
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getEntityTypeClass());
        Root<T> root = cq.from(getEntityTypeClass());
        cq.select(root);
        List<Predicate> andPredicatesList = new ArrayList<>();
        applyFindAllMode(andPredicatesList,root,cb);
        andPredicatesList.add(root.in((Collection<ID>) iterable));
        cq.where(
                cb.and(
                        andPredicatesList.toArray(new Predicate[andPredicatesList.size()])
                )
        );
        TypedQuery<T> typedQuery = em.createQuery(cq);
        return typedQuery.getResultList();
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getEntityTypeClass());
        Root<T> root = cq.from(getEntityTypeClass());
        cq.select(root);
        List<Predicate> andPredicatesList = new ArrayList<>();
        applyFindAllMode(andPredicatesList,root,cb);
        if(andPredicatesList.size()>0) {
            cq.where(andPredicatesList.get(0));
        }
        if (pageable.getSort() != null) {
            pageable.getSort().forEach(order -> {
                Iterator<String> propertyPartsIterator = Arrays.asList(order.getProperty().split("\\.")).iterator();
                Path path = root.get(propertyPartsIterator.next());
                while (propertyPartsIterator.hasNext()) {
                    path = path.get(propertyPartsIterator.next());
                }
                if (order.isAscending()) {
                    cq.orderBy(cb.asc(path));
                } else {
                    cq.orderBy(cb.desc(path));
                }
            });
        }
        TypedQuery<T> typedQuery = em.createQuery(cq);
        typedQuery.setFirstResult(pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());
        return new PageImpl<T>(typedQuery.getResultList(), pageable, count());
    }

    @Override
    public boolean exists(ID id) {
        return findOne(id) != null;
    }

    @Override
    public long count() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<T> root = cq.from(getEntityTypeClass());
        cq.select(cb.count(root));
        List<Predicate> andPredicatesList = new ArrayList<>();
        applyFindAllMode(andPredicatesList,root,cb);
        if(andPredicatesList.size()>0) {
            cq.where(andPredicatesList.get(0));
        }
        TypedQuery<Long> typedQuery = em.createQuery(cq);
        return typedQuery.getSingleResult();
    }
}
