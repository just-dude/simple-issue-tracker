package domain.common;

import common.beanFactory.BeanFactoryProvider;
import dao.common.exception.EntityWithSuchIdDoesNotExistsDaoException;
import domain.common.exception.BusinessException;
import domain.common.exception.EntityWithSuchIdDoesNotExistsBusinessException;
import domain.common.exception.ValidationFailedException;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import smartvalidation.constraintViolation.ConstraintViolation;
import smartvalidation.validator.entityValidator.EntityValidator;
import smartvalidation.validator.entityValidator.EntityValidatorFactory;

import java.io.Serializable;
import java.util.List;

public abstract class DomainObject<T, ID extends Serializable> implements Serializable, Validatable, SavableAndRemovable {

    protected EntityValidatorFactory evf;

    protected JpaRepository<T, ID> dao;


    @Override
    public boolean isValid() {
        return getEntityValidatorFactory().getValidator((T) this).isValid();
    }

    @Override
    public List<ConstraintViolation> getConstraintsViolations() {
        return getEntityValidatorFactory().getValidator((T) this).getConstraintViolations();
    }

    //@Transactional
    @Override
    public void save() throws BusinessException {
        ifUpdateNotExistsEntityThrowException();
        try {
            getDAO().save((T) this);
        } catch (Exception e) {
            throw new BusinessException("An error has occured on save entity", e, this.getClass().getName() + ".save", this);
        }
    }

    @Override
    public void remove() {
        try {
            getDAO().delete(getId());
        } catch (EntityWithSuchIdDoesNotExistsDaoException e) {
            throw new EntityWithSuchIdDoesNotExistsBusinessException(e.getId());
        } catch (Exception e) {
            throw new BusinessException("An error has occured on remove entity", e, this.getClass().getName() + ".remove", this);
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


    protected String getEntityValidatorFactoryName() {
        return StringUtils.uncapitalize(this.getClass().getSimpleName()) + "ValidatorFactory";
    }

    protected String getDaoName() {
        return StringUtils.uncapitalize(this.getClass().getSimpleName()) + "s" + "DAO";
    }

    protected abstract void ifUpdateNotExistsEntityThrowException() throws EntityWithSuchIdDoesNotExistsBusinessException;

    protected abstract ID getId();

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
