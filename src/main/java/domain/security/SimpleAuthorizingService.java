/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.security;

import domain.common.exception.AuthorizationFailedException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;

/**
 * @author SuslovAI
 */
public class SimpleAuthorizingService implements AuthorizingService {

    @Override
    public boolean isPermitted(String permission) {
        return SecurityUtils.getSubject().isPermitted(permission);
    }

    @Override
    public boolean[] isPermitted(String... permissions) {
        return SecurityUtils.getSubject().isPermitted(permissions);
    }

    @Override
    public boolean isPermittedAll(String... permissions) {
        return SecurityUtils.getSubject().isPermittedAll(permissions);
    }

    @Override
    public void checkPermission(String permission) throws AuthorizationFailedException {
        try {
            SecurityUtils.getSubject().checkPermission(permission);
        } catch (AuthorizationException e) {
            throw new AuthorizationFailedException(permission, e);
        }
    }

    @Override
    public void checkPermissions(String... permissions) throws AuthorizationFailedException {
        try {
            SecurityUtils.getSubject().checkPermissions(permissions);
        } catch (AuthorizationException e) {
            throw new AuthorizationFailedException(e, permissions);
        }
    }

    @Override
    public boolean hasRole(String roleName) {
        return SecurityUtils.getSubject().hasRole(roleName);
    }


}
