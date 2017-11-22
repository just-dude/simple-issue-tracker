package domain.common;

import common.argumentAssert.Assert;

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

    @Override
    protected void doRemove() {
        Assert.notNull(getId(),getClass().getSimpleName()+".id");
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
