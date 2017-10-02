package domain.common;

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
    protected void ifUpdateNotExistsEntityThrowException() throws EntityWithSuchIdDoesNotExistsBusinessException {
        if (!isNew() && !getDAO().exists(id)) {
            throw new EntityWithSuchIdDoesNotExistsBusinessException(id);
        }
    }

    protected boolean isNew() {
        return id == null;
    }

}
