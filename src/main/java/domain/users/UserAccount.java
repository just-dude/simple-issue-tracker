package domain.users;

import domain.common.HavingOneIdDomainObject;
import domain.common.exception.BusinessException;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

/**
 * Created by SuslovAI on 17.08.2017.
 */
@Entity(name = "UserAccount")
@Table(name = "UserAccounts")
public class UserAccount extends HavingOneIdDomainObject<UserAccount> {


    @Embedded
    private AccountInfo accountInfo;

    @Embedded
    private Profile profile;

    @Enumerated(EnumType.STRING)
    private UserGroup userGroup;

    public UserAccount() {
        this.setProfile(new Profile());
        this.setAccountInfo(new AccountInfo());
    }

    public UserAccount(Long id) {
        super(id);
        this.setProfile(new Profile());
        this.setAccountInfo(new AccountInfo());
    }

    public UserAccount(AccountInfo accountInfo, Profile profile) {
        this.accountInfo = accountInfo;
        this.profile = profile;
    }

    public UserAccount(AccountInfo accountInfo, Profile profile, UserGroup userGroup) {
        this.accountInfo = accountInfo;
        this.profile = profile;
        this.userGroup = userGroup;
    }

    public UserAccount(Long id, AccountInfo accountInfo, Profile profile) {
        super(id);
        this.accountInfo = accountInfo;
        this.profile = profile;
    }

    public UserAccount(Long id, AccountInfo accountInfo, Profile profile, UserGroup group) {
        super(id);
        this.accountInfo = accountInfo;
        this.profile = profile;
        this.userGroup = group;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }


    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + getId() +
                ", accountInfo=" + accountInfo +
                ", profile=" + profile +
                ", group=" + getUserGroup() +
                '}';
    }

    @Transactional
    @Override
    public <S extends UserAccount> S save() throws BusinessException {
        checkSavePermission();
        ifInvalidThrowValidationFailedException();
        ifUpdateNotExistsEntityThrowException();
        try {
            if (isNew()) {
                ByteSource salt = new SecureRandomNumberGenerator().nextBytes();
                String hashedPasswordBase64 = new Sha256Hash(this.getAccountInfo().getHashedPassword(), salt, 256).toBase64();
                this.getAccountInfo().setSalt(salt.getBytes());
                this.getAccountInfo().setHashedPassword(hashedPasswordBase64);
            } else {
                UserAccount entityInStorage = getFinder().getOne(getId());
                this.setAccountInfo(entityInStorage.getAccountInfo());
            }
            return doSave();
        } catch (Exception e) {
            throw new BusinessException("An error has occured on save entity", e, this.getClass().getName() + ".save", this);
        }
    }
}
