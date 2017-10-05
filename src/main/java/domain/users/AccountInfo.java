package domain.users;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

/**
 * Created by SuslovAI on 17.08.2017.
 */

@Embeddable
public class AccountInfo {

    private String login;

    private String hashedPassword;

    @Lob
    @Column(length = 1000)
    private byte[] salt;

    public AccountInfo() {
    }

    public AccountInfo(String login, String hashedPassword, byte[] salt) {
        this.login = login;
        this.hashedPassword = hashedPassword;
        this.salt = salt;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "AccountInfo{" +
                "login='" + login + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
