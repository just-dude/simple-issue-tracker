package domain.security.authorization;

import domain.common.exception.AuthorizationFailedException;
import domain.security.SecuritySubjectUtils;

import java.util.List;

/**
 * Created by Администратор on 31.10.2017.
 */
public class WithoutSoftDeletedFinderCheckPermissionsStrategy<T> implements FinderCheckPermissionsStrategy<T> {

    protected PermissionStringConstants permissionStringConsts;

    public WithoutSoftDeletedFinderCheckPermissionsStrategy(PermissionStringConstants permissionStringConsts) {
        this.permissionStringConsts = permissionStringConsts;
    }

    @Override
    public void checkFindAllPermission() throws AuthorizationFailedException {
        SecuritySubjectUtils.checkPermission(permissionStringConsts.getFindAllPermission());
    }

    @Override
    public void checkFindOnePermission(T entity) throws AuthorizationFailedException {
    }

    @Override
    public void checkFindAllByIdsPermission(List<T> entities) throws AuthorizationFailedException {
    }
}
