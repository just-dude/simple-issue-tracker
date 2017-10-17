package dao.common;


import common.argumentAssert.Assert;
import dao.common.exception.DataIntegrityViolationDaoException;
import dao.common.exception.EntityWithSuchIdDoesNotExistsDaoException;
import dao.common.exception.UnsupportedOperationDaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.*;

public class JpaDAO<T, ID extends Serializable> implements JpaRepository<T, ID> {


    private EntityManager em;

    protected Class<T> entityClass;


    public JpaDAO(EntityManager em, Class<T> entityClass) {
        this.em = em;
        this.entityClass = entityClass;
    }

    public Class<T> getEntityTypeClass() {
        return entityClass;
    }

    @Override
    public <S extends T> S save(S s) {
        Assert.notNull(s, "entity");
        return em.merge(s);
    }

    @Override
    public <S extends T> List<S> save(Iterable<S> iterable) {
        return saveLittle(iterable);
    }

    public <S extends T> List<S> saveLittle(Iterable<S> iterable) {
        Assert.notNull(iterable, "iterable of entities");
        List<S> result = new ArrayList<S>();
        for (S element : iterable) {
            result.add(this.save(element));
        }
        return result;
    }

    public <S extends T> List<S> saveLotsOf(Iterable<S> iterable) {
        Assert.notNull(iterable, "iterable of entities");
        List<S> result = new ArrayList<S>();
        int i = 0;
        for (S element : iterable) {
            result.add(this.save(element));
            if (++i % 50 == 0) {
                em.flush();
                em.clear();
            }
        }
        em.clear();
        return result;
    }


    @Override
    public void flush() {
        em.flush();
    }

    @Override
    public <S extends T> S saveAndFlush(S s) {
        S result = this.save(s);
        this.flush();
        return result;
    }

    @Override
    public void delete(ID id) {
        Assert.notNull(id, "id");
        T entity = findOne(id);
        if (entity == null) {
            throw new EntityWithSuchIdDoesNotExistsDaoException(id);
        }
        try {
            em.remove(entity);
            em.flush();
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationDaoException(e);
        } catch (Exception e) {
            throw new DataIntegrityViolationDaoException(e);
        }
    }

    @Override
    public void delete(T t) {
        Assert.notNull(t, "Entity %s to delete must not be null", t);
        if (!em.contains(t)) {
            t = em.merge(t);
        }
        try {
            em.remove(t);
            em.flush();
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationDaoException(e);
        }
    }

    @Override
    public void delete(Iterable<? extends T> iterable) {
        Assert.notNull(iterable, "iterable of entities");
        for (T entity : iterable) {
            delete(entity);
        }
    }

    @Override
    public void deleteAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<T> cd = cb.createCriteriaDelete(getEntityTypeClass());
        cd.from(getEntityTypeClass());
        em.createQuery(cd).executeUpdate();
    }

    @Override
    public void deleteInBatch(Iterable<T> iterable) {
        delete(iterable);
    }

    @Override
    public void deleteAllInBatch() {
        deleteAll();
    }

    @Override
    public T getOne(ID id) {
        return em.find(getEntityTypeClass(), id);
    }


    @Override
    public List<T> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getEntityTypeClass());
        Root<T> root = cq.from(getEntityTypeClass());
        cq.select(root);
        TypedQuery<T> typedQuery = em.createQuery(cq);
        return typedQuery.getResultList();
    }

    @Override
    public List<T> findAll(Sort sort) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getEntityTypeClass());
        Root<T> root = cq.from(getEntityTypeClass());
        cq.select(root);
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
            throw new IllegalArgumentException("Argument 'iterable' in 'JpaDao.findAll(Iterable<ID> iterable)' must be instanceof Collection");
        }
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getEntityTypeClass());
        Root<T> root = cq.from(getEntityTypeClass());
        cq.select(root);
        cq.where(root.in((Collection<ID>) iterable));
        TypedQuery<T> typedQuery = em.createQuery(cq);
        return typedQuery.getResultList();
    }


    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        throw new UnsupportedOperationDaoException("findAll(Example<S> example)");
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        throw new UnsupportedOperationDaoException("findAll(Example<S> example, Sort sort)");
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getEntityTypeClass());
        Root<T> root = cq.from(getEntityTypeClass());
        cq.select(root);
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
    public T findOne(ID id) {
        return em.find(getEntityTypeClass(), id);
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
        TypedQuery<Long> typedQuery = em.createQuery(cq);
        return typedQuery.getSingleResult();
    }


    @Override
    public <S extends T> S findOne(Example<S> example) {
        throw new UnsupportedOperationDaoException("findOne(Example<S> example)");
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        throw new UnsupportedOperationDaoException("findAll(Example<S> example, Pageable pageable)");
    }


    @Override
    public <S extends T> long count(Example<S> example) {
        throw new UnsupportedOperationDaoException("count(Example<S> example)");
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {
        throw new UnsupportedOperationDaoException("exists(Example<S> example)");
    }

    /*@Override
    public T getOne(ID id) {
        Field[] fields = id.getClass().getDeclaredFields();
        Map<String, Object> fieldNamesToValuesMap = new HashMap<>();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getEntityTypeClass());
        Root<T> entity = cq.from(getEntityTypeClass());
        cq.select(entity);

        for (Field field : fields) {
            try {

                String fieldValue = BeanUtils.getProperty(id, field.getName());
                cq.where(cb.equal(entity.get(field.getName()), fieldValue));
            } catch (Exception e) {
                throw new DaoException("An exception has occured on build where clause", e);
            }
        }
        TypedQuery<T> q = em.createQuery(cq);
        List<T> resultList = q.getResultList();

        return resultList.get(0);
    }*/
}
