package domain.common;

import domain.common.exception.DataAccessFailedBuisnessException;
import domain.common.exception.EntityWithSuchIdDoesNotExistsBusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;


public interface Finder<T extends DomainObject, ID extends Serializable> {

    T findOne(ID id) throws EntityWithSuchIdDoesNotExistsBusinessException, DataAccessFailedBuisnessException;

    T findOneWithInitPaths(ID id, HashSet<String> initPaths) throws EntityWithSuchIdDoesNotExistsBusinessException, DataAccessFailedBuisnessException;

    List<T> findAll(Iterable<ID> ids) throws DataAccessFailedBuisnessException;

    Page<T> findAll(Pageable var1) throws DataAccessFailedBuisnessException;

    List<T> findAll(Sort sort) throws DataAccessFailedBuisnessException;

    List<T> findAll() throws DataAccessFailedBuisnessException;
}
