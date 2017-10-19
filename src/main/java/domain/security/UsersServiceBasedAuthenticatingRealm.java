/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.security;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UsersServiceBasedAuthenticatingRealm extends AuthenticatingRealm {

    private static final Logger LOG = LoggerFactory.getLogger(UsersServiceBasedAuthenticatingRealm.class);
    private static final String realmName = "UsersServiceBasedRealm";

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();

        // Null username is invalid
        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }

        AuthenticationInfo info = null;
        /*try {

            UsersReaderService rs = (UsersReaderService) getBeanFactory().getBean("UsersReaderService");
            Optional<User> user = rs.findUserByLogin(upToken.getUsername());
            if (user.isPresent()) {
                UserAccount ua = user.get().getUserAccount();
                SimpleAuthenticationInfo saInfo = new SimpleAuthenticationInfo(
                        new SimplePrincipalCollection(new UserPrincipal(ua.getLogin(), ua.getUserId(), user.get().getUserGroup().getId()), realmName),
                        ua.getHashedPassword(),
                        ByteSource.Util.bytes(Base64.decode(ua.getSalt()))
                );
                return saInfo;
            }

        } catch (BusinessException e) {
            LOG.error("Exception has occured on getting user info", e);
            throw new AuthenticationException("Exception has occured on getting user info", e);
        }*/
        return null;
    }

}