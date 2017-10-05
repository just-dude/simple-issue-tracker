package domain.common;

import domain.common.exception.BusinessException;
import domain.common.exception.EntityWithSuchIdDoesNotExistsBusinessException;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class HavingOneIdDomainObject<T> extends DomainObject<T, Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public HavingOneIdDomainObject() {
    }

    public HavingOneIdDomainObject(Long id) {
        this.id = id;
    }


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    protected void ifUpdateNotExistsEntityThrowException() throws EntityWithSuchIdDoesNotExistsBusinessException, BusinessException {
        try {
            if (!isNew() && !getDAO().exists(id)) {
                throw new EntityWithSuchIdDoesNotExistsBusinessException(id);
            }
        } catch (EntityWithSuchIdDoesNotExistsBusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("An error has occured on save entity", e, this.getClass().getSimpleName() + ".save", this);
        }

    }


    protected boolean isNew() {
        return id == null;
    }

}
