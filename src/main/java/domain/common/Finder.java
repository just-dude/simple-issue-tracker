package domain.common;

import domain.common.exception.DataAccessFailedBuisnessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;


public interface Finder<T, ID extends Serializable> {

    T getOne(ID id) throws DataAccessFailedBuisnessException;

    List<T> findAll(Iterable<ID> ids) throws DataAccessFailedBuisnessException;

    Page<T> findAll(Pageable var1) throws DataAccessFailedBuisnessException;

    List<T> findAll(Sort sort) throws DataAccessFailedBuisnessException;

    List<T> findAll() throws DataAccessFailedBuisnessException;
}
