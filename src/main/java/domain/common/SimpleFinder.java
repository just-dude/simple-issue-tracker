package domain.common;

import com.google.common.collect.Iterables;
import common.argumentAssert.Assert;
import domain.common.exception.DataAccessFailedBuisnessException;
import domain.common.exception.EntityWithSuchIdDoesNotExistsBusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by SuslovAI on 05.10.2017.
 */
public class SimpleFinder<T, ID extends Serializable> implements Finder<T, ID> {

    protected JpaRepository<T, ID> dao;


    public SimpleFinder(JpaRepository dao) {
        this.dao = dao;
    }

    @Override
    public T getOne(ID id) throws EntityWithSuchIdDoesNotExistsBusinessException, DataAccessFailedBuisnessException {
        try {
            Assert.notNull(id,"id");
            T result = dao.getOne(id);
            if (result == null) {
                throw new EntityWithSuchIdDoesNotExistsBusinessException(id);
            }
            return result;
        } catch (EntityWithSuchIdDoesNotExistsBusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new DataAccessFailedBuisnessException("An error has occured on read from data storage", e, this.getClass().getSimpleName() + ".getOne(Id id)", id);
        }
    }

    @Override
    public List<T> findAll(Iterable<ID> ids) throws DataAccessFailedBuisnessException {
        try {
            Assert.notNull(ids,"ids");
            Assert.notContainsNullElements((Collection<ID>) ids,"ids");
            return dao.findAll(ids);
        } catch (Exception e) {
            throw new DataAccessFailedBuisnessException("An error has occured on read from data storage", e, this.getClass().getSimpleName() + ".findAll(Iterable<ID> ids)", Iterables.toString(ids));
        }
    }

    @Override
    public Page<T> findAll(Pageable pageable) throws DataAccessFailedBuisnessException {
        try {
            Assert.notNull(pageable,"pageable");
            return dao.findAll(pageable);
        } catch (Exception e) {
            throw new DataAccessFailedBuisnessException("An error has occured on read from data storage", e, this.getClass().getSimpleName() + ".findAll(Pageable pageable)", pageable);
        }
    }

    @Override
    public List<T> findAll(Sort sort) throws DataAccessFailedBuisnessException {
        try {
            Assert.notNull(sort,"sort");
            return dao.findAll(sort);
        } catch (Exception e) {
            throw new DataAccessFailedBuisnessException("An error has occured on read from data storage", e, this.getClass().getSimpleName() + ".findAll(Sort sort)", sort);
        }
    }

    @Override
    public List<T> findAll() throws DataAccessFailedBuisnessException {
        try {
            return dao.findAll();
        } catch (Exception e) {
            throw new DataAccessFailedBuisnessException("An error has occured on read from data storage", e, this.getClass().getSimpleName() + ".findAll()");
        }
    }
}
