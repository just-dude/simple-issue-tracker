package domain.users.authorization;

import domain.common.exception.AuthorizationFailedException;
import domain.security.authorization.FinderCheckPermissionsStrategy;
import domain.users.UserAccount;

/**
 * Created by Администратор on 31.10.2017.
 */
public interface UserAccountsFinderCheckPermissionStrategy extends FinderCheckPermissionsStrategy<UserAccount> {
    public void checkFindByLoginPermission(UserAccount entity) throws AuthorizationFailedException;
}
