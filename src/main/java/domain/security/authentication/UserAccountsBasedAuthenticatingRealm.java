/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.security.authentication;

import common.beanFactory.BeanFactoryProvider;
import domain.common.exception.BusinessException;
import domain.security.UserPrincipal;
import domain.users.UserAccount;
import domain.users.UserAccountsFinder;
import org.apache.shiro.authc.*;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserAccountsBasedAuthenticatingRealm extends AuthenticatingRealm {

    private static final Logger LOG = LoggerFactory.getLogger(UserAccountsBasedAuthenticatingRealm.class);
    private static final String realmName = "UserAccountsBasedRealm";

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();

        // Null username is invalid
        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }

        AuthenticationInfo info = null;
        try {

            UserAccountsFinder userAccountsFinder = (UserAccountsFinder) BeanFactoryProvider.getBeanFactory().getBean("userAccountsFinder");
            UserAccount userAccount = userAccountsFinder.findByLogin(upToken.getUsername());
            if (userAccount != null) {
                SimpleAuthenticationInfo saInfo = new SimpleAuthenticationInfo(
                        new SimplePrincipalCollection(
                                new UserPrincipal(userAccount.getAccountInfo().getLogin(), userAccount.getId(), userAccount.getUserGroup()), realmName),
                        userAccount.getAccountInfo().getHashedPassword(),
                        ByteSource.Util.bytes(Base64.decode(userAccount.getAccountInfo().getSalt()))
                );
                return saInfo;
            } else {
                return null;
            }

        } catch (BusinessException e) {
            LOG.error("Exception has occured on getting user info", e);
            throw new AuthenticationException("Exception has occured on getting user info", e);
        }
    }

}