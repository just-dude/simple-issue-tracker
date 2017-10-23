package domain.security;

import common.beanFactory.BeanFactoryProvider;
import domain.common.exception.AuthorizationFailedException;
import domain.common.exception.BusinessException;
import domain.common.exception.ValidationFailedException;
import domain.users.UserAccount;
import domain.users.UserAccountsFinder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import smartvalidation.constraintDescription.ConstraintDescription;
import smartvalidation.constraintViolation.EntityConstraintViolation;

import java.util.Collections;

/**
 * Created by SuslovAI on 23.10.2017.
 */
public class SecuritySubject {

    private static String CURRENT_USER_ACCOUNT_SESSION_KEY = "currentUserAccount";

    public void login(String login, String password) throws BusinessException {
        UsernamePasswordToken token = new UsernamePasswordToken(login, password);
        try {
            SecurityUtils.getSubject().login(token);
            UserAccountsFinder finder = (UserAccountsFinder) BeanFactoryProvider.getBeanFactory().getBean("userAccountsFinder");
            Long currentUserId = ((UserPrincipal) SecurityUtils.getSubject().getPrincipals().byType(UserPrincipal.class)).getUserId();
            UserAccount currentUserAccount = finder.getOne(currentUserId);
            SecurityUtils.getSubject().getSession().setAttribute(CURRENT_USER_ACCOUNT_SESSION_KEY, currentUserAccount);
        } catch (AuthenticationException e) {
            throw new ValidationFailedException(Collections.singletonList(
                    new EntityConstraintViolation(new ConstraintDescription("invalid.login.or.password"), "userLoginAndPassword")
            ));
        } catch (Exception e) {
            throw new BusinessException("An error occured on login", e);
        }
    }

    public void logout() {
        try {
            SecurityUtils.getSubject().logout();
            SecurityUtils.getSubject().getSession().removeAttribute(CURRENT_USER_ACCOUNT_SESSION_KEY);
        } catch (Exception e) {
            throw new BusinessException("An error occured on logout", e);
        }
    }

    public UserAccount getCurrentUserAccount() {
        UserAccount currentUserAccount = (UserAccount) SecurityUtils.getSubject().getSession().getAttribute(CURRENT_USER_ACCOUNT_SESSION_KEY);
        return currentUserAccount;
    }

    public boolean isPermitted(String permission) throws BusinessException {
        try {
            return SecurityUtils.getSubject().isPermitted(permission);
        } catch (Exception e) {
            throw new BusinessException("An error has occuren on isPermitted method", e);
        }
    }

    public boolean[] isPermitted(String... permissions) throws BusinessException {
        try {
            return SecurityUtils.getSubject().isPermitted(permissions);
        } catch (Exception e) {
            throw new BusinessException("An error has occuren on isPermitted method", e);
        }
    }

    public boolean isPermittedAll(String... permissions) throws BusinessException {
        try {
            return SecurityUtils.getSubject().isPermittedAll(permissions);
        } catch (Exception e) {
            throw new BusinessException("An error has occuren on isPermittedAll method", e);
        }
    }

    public void checkPermission(String permission) throws AuthorizationFailedException, BusinessException {
        try {
            SecurityUtils.getSubject().checkPermission(permission);
        } catch (AuthorizationException e) {
            throw new AuthorizationFailedException(permission, e);
        } catch (Exception e) {
            throw new BusinessException("An error has occuren on checkPermission method", e);
        }
    }

    public void checkPermissions(String... permissions) throws AuthorizationFailedException, BusinessException {
        try {
            SecurityUtils.getSubject().checkPermissions(permissions);
        } catch (AuthorizationException e) {
            throw new AuthorizationFailedException(e, permissions);
        } catch (Exception e) {
            throw new BusinessException("An error has occuren on checkPermissions method", e);
        }
    }

    public boolean hasRole(String roleName) throws BusinessException {
        try {
            return SecurityUtils.getSubject().hasRole(roleName);
        } catch (Exception e) {
            throw new BusinessException("An error has occuren on hasRole method", e);
        }
    }
}
