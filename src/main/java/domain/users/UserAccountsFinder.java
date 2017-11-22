package domain.users;

import common.argumentAssert.Assert;
import dao.users.UserAccountsJpaDao;
import domain.common.SimpleFinder;
import domain.common.exception.BusinessException;
import domain.users.authorization.UserAccountsFinderCheckPermissionStrategy;
import domain.users.authorization.WithoutSoftDeletedUserAccountsFinderCheckPermissionStrategy;

/**
 * Created by SuslovAI on 23.10.2017.
 */
public class UserAccountsFinder extends SimpleFinder<UserAccount, Long> {

    public UserAccountsFinder(UserAccountsJpaDao dao) {
        super(dao, WithoutSoftDeletedUserAccountsFinderCheckPermissionStrategy.INSTANCE);
    }

    public UserAccountsFinder(UserAccountsJpaDao dao,UserAccountsFinderCheckPermissionStrategy checkPermissionStrategy) {
        super(dao,checkPermissionStrategy);
    }

    public UserAccount findByLogin(String login) {
        try {
            Assert.notNull(login,"login");
            UserAccount result = ((UserAccountsJpaDao) dao).findByLogin(login);
            ((UserAccountsFinderCheckPermissionStrategy)checkPermissionsStrategy).checkFindByLoginPermission(result);
            return result;
        } catch (Exception e) {
            throw new BusinessException("Exception has occured", e);
        }
    }
}
