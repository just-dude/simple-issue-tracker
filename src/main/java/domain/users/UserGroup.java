package domain.users;

import domain.common.DomainObject;

import javax.persistence.*;

/**
 * Created by SuslovAI on 18.08.2017.
 */

@Entity( name = "UserGroup" )
@Table(name = "UserGroups")
public class UserGroup extends DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public UserGroup() {
    }

    public UserGroup(Long id,String name) {
        this.name = name;
        this.id=id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
