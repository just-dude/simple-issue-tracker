package domain.users;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by SuslovAI on 17.08.2017.
 */
@Embeddable
public class Profile implements Serializable {

    private String name;

    private String surname;

    private String email;

    public Profile() {
    }

    public Profile(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return getSurname() + " " + getName();
    }


    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
