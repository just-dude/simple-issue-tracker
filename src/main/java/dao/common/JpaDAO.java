package dao.common;


import common.argumentAssert.Assert;
import dao.common.exception.EntityWithSuchIdDoesNotExistsDaoException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
        this.save(s);
        this.flush();
        return s;
    }

    @Override
    public void delete(ID id) {
        Assert.notNull(id, "id");
        T entity = findOne(id);
        if (entity == null) {
            throw new EntityWithSuchIdDoesNotExistsDaoException(id);
        }
        em.remove(entity);
    }

    @Override
    public void delete(T t) {
        Assert.notNull(t, "Entity %s to delete must not be null", t);
        if (!em.contains(t)) {
            t = em.merge(t);
        }
        em.remove(t);
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
    public T getOne(ID id) {
        return em.find(getEntityTypeClass(), id);
    }


    @Override
    public List<T> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getEntityTypeClass());
        Root<T> entity = cq.from(getEntityTypeClass());
        cq.select(entity);
        TypedQuery<T> typedQuery = em.createQuery(cq);
        return typedQuery.getResultList();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return null;
    }

    @Override
    public List<T> findAll(Iterable<ID> iterable) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<T> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }


    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return null;
    }


    @Override
    public T findOne(ID id) {
        return em.find(getEntityTypeClass(), id);
    }

    @Override
    public boolean exists(ID id) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }


    @Override
    public <S extends T> S findOne(Example<S> example) {
        return null;
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends T> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {
        return false;
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
