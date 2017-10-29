package javaConfig.spring.test;

import common.beanFactory.BeanFactoryProvider;
import domain.common.exception.BusinessException;
import domain.issues.authorization.IssueStatesAuthorizingRealm;
import domain.issues.authorization.IssueTypesAuthorizingRealm;
import domain.issues.authorization.IssuesAuthorizingRealm;
import domain.security.SecuritySubjectUtils;
import domain.security.UserPrincipal;
import domain.security.authentication.UserAccountsBasedAuthenticatingRealm;
import domain.security.authorization.DomainObjectSpecificWildcardPermissionResolver;
import domain.security.authorization.DomainObjectSpecificatedRealmAuthorizer;
import domain.users.UserAccount;
import domain.users.UserAccountsFinder;
import domain.users.UserGroup;
import domain.users.authorization.UserAccountsAuthorizingRealm;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SuslovAI on 19.10.2017.
 */
public class SecurityConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setLoginUrl("/login.html");
        shiroFilter.setSuccessUrl("/index.html");
        shiroFilter.setSuccessUrl("/unauthorized.html");
        return shiroFilter;
    }

    @Bean
    public SecurityManager securityManager(DomainObjectSpecificatedRealmAuthorizer domainObjectSpecificatedRealmAuthorizer, SessionManager sessionManager,
                                           UserAccountsBasedAuthenticatingRealm userAccountsBasedAuthenticatingRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(
                Arrays.asList(
                        domainObjectSpecificatedRealmAuthorizer,
                        new AuthenticatingRealmStub()
                        // add new authentication realms here
                )
        );
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setGlobalSessionTimeout(3600000);
        return sessionManager;
    }

    @Bean
    public DomainObjectSpecificatedRealmAuthorizer domainObjectSpecificatedRealmAuthorizer() {
        Map<String, Realm> realmsMap = new HashMap();
        // write realm keys in lower case!
        realmsMap.put("useraccount", new UserAccountsAuthorizingRealm());
        realmsMap.put("issue", new IssuesAuthorizingRealm());
        realmsMap.put("issuestate", new IssueStatesAuthorizingRealm());
        realmsMap.put("issuetype", new IssueTypesAuthorizingRealm());
        // add new authorizing realms here!
        DomainObjectSpecificatedRealmAuthorizer domainObjectSpecificatedRealmAuthorizer = new DomainObjectSpecificatedRealmAuthorizer(realmsMap);
        domainObjectSpecificatedRealmAuthorizer.setPermissionResolver(new DomainObjectSpecificWildcardPermissionResolver());
        return domainObjectSpecificatedRealmAuthorizer;
    }

    @Bean
    public SecuritySubjectUtils securitySubject() {
        return new SecuritySubjectUtils();
    }

    @Bean
    public UserAccountsBasedAuthenticatingRealm userAccountsBasedAuthenticatingRealm() {
        UserAccountsBasedAuthenticatingRealm realm = new UserAccountsBasedAuthenticatingRealm();
        realm.setCredentialsMatcher(new SimpleCredentialsMatcher());
        return realm;
    }

    public static class AuthenticatingRealmStub extends AuthenticatingRealm {

        private static final Logger LOG = LoggerFactory.getLogger(AuthenticatingRealmStub.class);
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
                if(username=="admin") {
                    return new SimpleAuthenticationInfo(
                            new SimplePrincipalCollection(
                                    new UserPrincipal("admin", 1L, UserGroup.Admin), realmName
                            ),
                            "123"
                    );
                } else if(username=="firstCommonUser"){
                    return new SimpleAuthenticationInfo(
                            new SimplePrincipalCollection(
                                    new UserPrincipal("firstCommonUser", 2L, UserGroup.CommonUser), realmName
                            ),
                            "234"
                    );
                } else if(username=="secondCommonUser"){
                    return new SimpleAuthenticationInfo(
                            new SimplePrincipalCollection(
                                    new UserPrincipal("secondCommonUser", 3L, UserGroup.CommonUser), realmName
                            ),
                            "234"
                    );
                } else {
                    return null;
                }

            } catch (BusinessException e) {
                LOG.error("Exception has occured on getting user info", e);
                throw new AuthenticationException("Exception has occured on getting user info", e);
            }
        }

    }
}
