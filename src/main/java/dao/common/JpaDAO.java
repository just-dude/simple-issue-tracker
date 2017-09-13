package dao.common;


import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class JpaDAO<T, ID extends Serializable> implements JpaRepository<T, ID> {


    private EntityManager em;

    public JpaDAO(EntityManager em) {
        this.em = em;
    }

    protected Class<T> getEntityTypeClass() {
        return (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public <S extends T> S save(S s) {
        return em.merge(s);
    }

    @Override
    public <S extends T> List<S> save(Iterable<S> iterable) {
        List<S> result = new ArrayList<S>();
        for (S element : iterable) {
            this.save(element);
        }
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
        if (em.contains())
            em.remove(em.find(getEntityTypeClass(), id));
    }

    @Override
    public void delete(T t) {

    }

    @Override
    public void delete(Iterable<? extends T> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<T> findAll() {
        return null;
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
    public T getOne(ID id) {
        return null;
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
}
