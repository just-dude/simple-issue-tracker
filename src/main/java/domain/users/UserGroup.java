package domain.users;

import domain.common.HavingNameAndOneIdDomainObject;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by SuslovAI on 18.08.2017.
 */

@Entity(name = "UserGroup")
@Table(name = "UserGroups")
public class UserGroup extends HavingNameAndOneIdDomainObject<UserGroup> {

    public enum StaticGroups {


        public void StaticGroups(Long id, String name) {

        }
    }

    public UserGroup() {
    }

    public UserGroup(Long id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "UserGroup{ id=" + id + ", name=" + name + " }";
    }
}
