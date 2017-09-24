package domain.common;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class HavingNameAndOneIdDomainObject extends HavingOneIdDomainObject {

    protected String name;

    public HavingNameAndOneIdDomainObject() {
    }

    public HavingNameAndOneIdDomainObject(Long id, String name) {
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