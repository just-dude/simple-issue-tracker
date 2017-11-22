package domain.users.authorization;

import domain.common.exception.AuthorizationFailedException;
import domain.security.authorization.WithoutSoftDeletedFinderCheckPermissionsStrategy;
import domain.users.UserAccount;

public class WithoutSoftDeletedUserAccountsFinderCheckPermissionStrategy extends WithoutSoftDeletedFinderCheckPermissionsStrategy<UserAccount>
        implements UserAccountsFinderCheckPermissionStrategy {

    public static UserAccountsFinderCheckPermissionStrategy INSTANCE = new WithoutSoftDeletedUserAccountsFinderCheckPermissionStrategy();

    public WithoutSoftDeletedUserAccountsFinderCheckPermissionStrategy() {
        super(UserAccountsPermissionStringConstants.INSTANCE);
    }

    @Override
    public void checkFindOnePermission(UserAccount entity) throws AuthorizationFailedException {
        UserAccountsPermissionStringConstants.INSTANCE.getFindOneByIdPermission(entity.getId());
    }

    @Override
    public void checkFindByLoginPermission(UserAccount entity) throws AuthorizationFailedException {
    }
}
