package javaConfig.spring.common;

import domain.security.ModuleSpecificWildcardPermissionResolver;
import domain.security.NamedModularRealmAuthorizer;
import domain.security.SimpleAuthorizingService;
import domain.security.UsersServiceBasedAuthenticatingRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SuslovAI on 19.10.2017.
 */
public class SecurityConfig {

    @Autowired
    public UsersServiceBasedAuthenticatingRealm usersServiceBasedAuthenticatingRealm;

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
    public SecurityManager securityManager(NamedModularRealmAuthorizer namedModularRealmAuthorizer, SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(Arrays.asList(namedModularRealmAuthorizer));
        securityManager.setSessionManager(sessionManager);
        securityManager.getRealms().add(usersServiceBasedAuthenticatingRealm);
        // add new realms here!
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
    public NamedModularRealmAuthorizer namedModularRealmAuthorizer() {
        Map<String, Realm> realmsMap = new HashMap();
        //realmsMap.put("users", new UsersAuthorizingRealm());
        NamedModularRealmAuthorizer namedModularRealmAuthorizer = new NamedModularRealmAuthorizer(realmsMap);
        namedModularRealmAuthorizer.setPermissionResolver(new ModuleSpecificWildcardPermissionResolver());
        return namedModularRealmAuthorizer;
    }

    @Bean
    public SimpleAuthorizingService simpleAuthorizingService() {
        return new SimpleAuthorizingService();
    }
}
