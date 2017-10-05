package domain.common;

import common.beanFactory.BeanFactoryProvider;
import dao.common.exception.EntityWithSuchIdDoesNotExistsDaoException;
import domain.common.exception.BusinessException;
import domain.common.exception.DataAccessFailedBuisnessException;
import domain.common.exception.EntityWithSuchIdDoesNotExistsBusinessException;
import domain.common.exception.ValidationFailedException;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import smartvalidation.constraintViolation.ConstraintViolation;
import smartvalidation.exception.EntityValidationException;
import smartvalidation.validator.entityValidator.EntityValidator;
import smartvalidation.validator.entityValidator.EntityValidatorFactory;

import java.io.Serializable;
import java.util.List;

public abstract class DomainObject<T, ID extends Serializable> implements Serializable, Validatable, SavableAndRemovable<T> {

    protected EntityValidatorFactory evf;
    protected EntityValidator currentValidator;

    protected JpaRepository<T, ID> dao;


    @Override
    public boolean isValid() throws BusinessException {
        try {
            currentValidator = getEntityValidatorFactory().getValidator((T) this);
            return currentValidator.isValid();
        } catch (EntityValidationException e) {
            throw new BusinessException("On entity validation error has occured", e, getClass().getSimpleName() + ".isValid", this);
        }
    }

    @Override
    public List<ConstraintViolation> getConstraintsViolations() throws BusinessException {
        if (currentValidator == null) {
            throw new BusinessException("Entity not yet validated. Can't get constraints violations before validate entity");
        }
        try {
            return currentValidator.getConstraintViolations();
        } catch (EntityValidationException e) {
            throw new BusinessException("On entity validation error has occured", e, getClass().getSimpleName() + ".getConstraintsViolations", this);
        }
    }

    @Transactional
    @Override
    public <S extends T> S save() throws DataAccessFailedBuisnessException {
        ifUpdateNotExistsEntityThrowException();
        try {
            return doSave();
        } catch (Exception e) {
            throw new DataAccessFailedBuisnessException("An error has occured on save entity", e, this.getClass().getName() + ".save", this);
        }
    }


    @Transactional
    @Override
    public void remove() throws DataAccessFailedBuisnessException, EntityWithSuchIdDoesNotExistsBusinessException {
        try {
            getDAO().delete(getId());
        } catch (EntityWithSuchIdDoesNotExistsDaoException e) {
            throw new EntityWithSuchIdDoesNotExistsBusinessException(e.getId());
        } catch (Exception e) {
            throw new DataAccessFailedBuisnessException("An error has occured on remove entity", e, this.getClass().getName() + ".remove", this);
        }
    }

    protected void ifInvalidThrowValidationFailedException() throws ValidationFailedException, BusinessException {
        try {
            EntityValidator validator = getEntityValidatorFactory().getValidator((T) this);
            if (!validator.isValid()) {
                throw new ValidationFailedException(validator.getConstraintViolations());
            }
        } catch (Exception e) {
            throw new BusinessException("An error has occured on validation entity", e, this.getClass().getName() + ".ifInvalidThrowValidationFailedException", this);
        }
    }

    protected <S extends T> S doSave() {
        return (S) getDAO().save((T) this);
    }


    protected String getEntityValidatorFactoryName() {
        String validatorFactorySufix = (isNew()) ? "OnCreateValidatorFactory" : "OnUpdateValidatorFactory";
        return StringUtils.uncapitalize(this.getClass().getSimpleName()) + validatorFactorySufix;
    }

    protected String getDaoName() {
        return StringUtils.uncapitalize(this.getClass().getSimpleName()) + "s" + "DAO";
    }

    protected abstract void ifUpdateNotExistsEntityThrowException() throws EntityWithSuchIdDoesNotExistsBusinessException;

    protected abstract ID getId();

    protected abstract boolean isNew();


    protected EntityValidatorFactory<T> getEntityValidatorFactory() {
        if (evf == null) {
            evf = (EntityValidatorFactory<T>) BeanFactoryProvider.getBeanFactory().getBean(getEntityValidatorFactoryName());
            if (evf == null) {
                throw new BusinessException("EntityValidatorFactory with name [" + getEntityValidatorFactoryName() + "] not define in spring context");
            }
        }
        return evf;
    }

    protected JpaRepository<T, ID> getDAO() {
        if (dao == null) {
            dao = (JpaRepository<T, ID>) BeanFactoryProvider.getBeanFactory().getBean(getDaoName());
            if (dao == null) {
                throw new BusinessException("DAO for [" + this.getClass().getName() + "] entity with name [" + getDaoName() + "] not define in spring context");
            }
        }
        return dao;
    }


}
