package domain.common;

import com.google.common.collect.Iterables;
import common.argumentAssert.Assert;
import dao.common.Dao;
import domain.common.exception.DataAccessFailedBuisnessException;
import domain.common.exception.EntityWithSuchIdDoesNotExistsBusinessException;
import domain.security.authorization.FinderCheckPermissionsStrategy;
import domain.security.authorization.PermissionStringConstants;
import domain.security.authorization.WithoutSoftDeletedFinderCheckPermissionsStrategy;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;


public class SimpleFinder<T extends DomainObject, ID extends Serializable> implements Finder<T, ID> {

    protected Dao<T, ID> dao;
    protected FinderCheckPermissionsStrategy checkPermissionsStrategy;

    public SimpleFinder(Dao dao, FinderCheckPermissionsStrategy checkPermissionsStrategy) {
        this.dao = dao;
        this.checkPermissionsStrategy=checkPermissionsStrategy;
    }

    public SimpleFinder(Dao dao, Class domainObjectClass) {
        this.dao = dao;
        checkPermissionsStrategy=new WithoutSoftDeletedFinderCheckPermissionsStrategy(
                new PermissionStringConstants(StringUtils.uncapitalize(domainObjectClass.getSimpleName()))
        );
    }

    @Override
    public T findOne(ID id) throws EntityWithSuchIdDoesNotExistsBusinessException, DataAccessFailedBuisnessException {
        try {
            Assert.notNull(id,"id");
            T result = dao.findOne(id);
            if (result == null) {
                throw new EntityWithSuchIdDoesNotExistsBusinessException(id);
            }
            checkPermissionsStrategy.checkFindOnePermission(result);
            return result;
        } catch (EntityWithSuchIdDoesNotExistsBusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new DataAccessFailedBuisnessException("An error has occured on read from data storage", e, this.getClass().getSimpleName() + ".findOne(Id id)", id);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public T findOneWithInitPaths(ID id,HashSet<String> initPaths) throws EntityWithSuchIdDoesNotExistsBusinessException, DataAccessFailedBuisnessException {
        try {
            Assert.notNull(id,"id");
            T result = dao.findOne(id);
            if (result == null) {
                throw new EntityWithSuchIdDoesNotExistsBusinessException(id);
            }
            checkPermissionsStrategy.checkFindOnePermission(result);
            result.initializePaths(initPaths);
            return result;
        } catch (EntityWithSuchIdDoesNotExistsBusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new DataAccessFailedBuisnessException("An error has occured on read from data storage", e, this.getClass().getSimpleName() + ".findOne(Id id)", id);
        }
    }

    @Override
    public List<T> findAll(Iterable<ID> ids) throws DataAccessFailedBuisnessException {
        try {
            Assert.notNull(ids,"ids");
            Assert.notContainsNullElements((Collection<ID>) ids,"ids");
            List<T> result= dao.findAll(ids);
            checkPermissionsStrategy.checkFindAllByIdsPermission(result);
            return result;
        } catch (Exception e) {
            throw new DataAccessFailedBuisnessException("An error has occured on read from data storage", e, this.getClass().getSimpleName() + ".findAll(Iterable<ID> ids)", Iterables.toString(ids));
        }
    }

    @Override
    public Page<T> findAll(Pageable pageable) throws DataAccessFailedBuisnessException {
        try {
            Assert.notNull(pageable,"pageable");
            checkPermissionsStrategy.checkFindAllPermission();
            return dao.findAll(pageable);
        } catch (Exception e) {
            throw new DataAccessFailedBuisnessException("An error has occured on read from data storage", e, this.getClass().getSimpleName() + ".findAll(Pageable pageable)", pageable);
        }
    }

    @Override
    public List<T> findAll(Sort sort) throws DataAccessFailedBuisnessException {
        try {
            Assert.notNull(sort,"sort");
            checkPermissionsStrategy.checkFindAllPermission();
            return dao.findAll(sort);
        } catch (Exception e) {
            throw new DataAccessFailedBuisnessException("An error has occured on read from data storage", e, this.getClass().getSimpleName() + ".findAll(Sort sort)", sort);
        }
    }

    @Override
    public List<T> findAll() throws DataAccessFailedBuisnessException {
        try {
            checkPermissionsStrategy.checkFindAllPermission();
            return dao.findAll();
        } catch (Exception e) {
            throw new DataAccessFailedBuisnessException("An error has occured on read from data storage", e, this.getClass().getSimpleName() + ".findAll()");
        }
    }

}
