package domain.common;

import common.argumentAssert.Assert;
import dao.common.exception.EntityWithSuchIdDoesNotExistsDaoException;
import domain.common.exception.BusinessException;
import domain.common.exception.DataAccessFailedBuisnessException;
import domain.common.exception.DataIntegrityViolationBusinessException;
import domain.common.exception.EntityWithSuchIdDoesNotExistsBusinessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class HavingSoftDeletedDomainObject<T extends HavingSoftDeletedDomainObject, ID extends Serializable>
        extends DomainObject<T,ID> {

    protected Boolean isSoftDeleted=false;

    public HavingSoftDeletedDomainObject() {
    }

    public Boolean isSoftDeleted() {
        return (isSoftDeleted==null)?false:isSoftDeleted;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void hardRemove() throws DataAccessFailedBuisnessException, EntityWithSuchIdDoesNotExistsBusinessException, DataIntegrityViolationBusinessException {
        Assert.notNull(getId(),getClass().getSimpleName()+".id");
        try {
            doHardRemove();
        } catch (EntityWithSuchIdDoesNotExistsDaoException e) {
            throw new EntityWithSuchIdDoesNotExistsBusinessException(e.getId());
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationBusinessException(e);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new DataAccessFailedBuisnessException("An error has occured on remove entity", e, this.getClass().getName() + ".remove", this);
        }
    }


    @Transactional
    public void softRemove() throws DataAccessFailedBuisnessException, EntityWithSuchIdDoesNotExistsBusinessException, DataIntegrityViolationBusinessException {
        Assert.notNull(getId(),getClass().getSimpleName()+".id");
        try {
            doSoftRemove();
        } catch (EntityWithSuchIdDoesNotExistsDaoException e) {
            throw new EntityWithSuchIdDoesNotExistsBusinessException(e.getId());
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationBusinessException(e);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new DataAccessFailedBuisnessException("An error has occured on remove entity", e, this.getClass().getName() + ".remove", this);
        }
    }

    @Override
    protected void doRemove() {
        try {
            hardRemove();
        } catch (DataIntegrityViolationBusinessException e) {
            softRemove();
        }
    }

    protected void doHardRemove()throws JpaSystemException{
        getDao().deleteAndFlush(getId());
    }

    protected void doSoftRemove(){
        T entityInStorage = getFinder().findOne(getId());
        entityInStorage.setSoftDeleted();
        entityInStorage.doSave();
    }

    public void setSoftDeleted() {
        isSoftDeleted = true;
    }

    public void unsetSoftDeleted() {
        isSoftDeleted = false;
    }
}
