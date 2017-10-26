package javaConfig.spring.dev;


import domain.security.SecuritySubjectUtils;
import domain.security.authentication.UserAccountsBasedAuthenticatingRealm;
import domain.security.authorization.DomainObjectSpecificWildcardPermissionResolver;
import domain.security.authorization.DomainObjectSpecificatedRealmAuthorizer;
import domain.users.authorization.UserAccountsAuthorizingRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
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
                        userAccountsBasedAuthenticatingRealm
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
        HashedCredentialsMatcher hcm = new HashedCredentialsMatcher();
        hcm.setHashAlgorithmName("SHA-256");
        hcm.setHashIterations(256);
        hcm.setStoredCredentialsHexEncoded(false);
        realm.setCredentialsMatcher(hcm);
        return realm;
    }

}
