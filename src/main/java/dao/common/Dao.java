package dao.common;

import dao.common.exception.EntityWithSuchIdDoesNotExistsDaoException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Администратор on 29.10.2017.
 */
public interface Dao<T, ID extends Serializable> {

    <S extends T> S save(S s);

    <S extends T> List<S> save(Iterable<S> iterable);

    void flush();

    <S extends T> S saveAndFlush(S s);

    void deleteAndFlush(ID id) throws EntityWithSuchIdDoesNotExistsDaoException;

    void deleteAndFlush(T t);

    void deleteAndFlush(Iterable<? extends T> iterable);

    void deleteAll();

    void deleteInBatch(Iterable<T> iterable);

    void deleteAllInBatch();

    void clearPersistenceContext();

    T findOne(ID id);

    List<T> findAll();

    List<T> findAll(Sort sort);

    List<T> findAll(Iterable<ID> iterable);

    Page<T> findAll(Pageable pageable);

    boolean exists(ID id);

    long count();
}
