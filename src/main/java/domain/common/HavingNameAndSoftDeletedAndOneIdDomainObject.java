package domain.common;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class HavingNameAndSoftDeletedAndOneIdDomainObject<T extends HavingOneIdAndSoftDeletedDomainObject>
        extends HavingOneIdAndSoftDeletedDomainObject<T> {

    protected String name;

    public HavingNameAndSoftDeletedAndOneIdDomainObject() {
    }

    public HavingNameAndSoftDeletedAndOneIdDomainObject(Long id, String name) {
        super(id);
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
