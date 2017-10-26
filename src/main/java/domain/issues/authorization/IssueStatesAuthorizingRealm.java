    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.issues.authorization;

import domain.security.UserPrincipal;
import domain.security.authorization.AbstractAuthorizingRealm;
import domain.security.authorization.OnlyAdminHasPermissionsAuthorizingRealm;
import domain.users.UserGroup;
import org.apache.shiro.authz.SimpleRole;
import org.apache.shiro.authz.permission.WildcardPermission;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author SuslovAI
 */

public class IssueStatesAuthorizingRealm extends OnlyAdminHasPermissionsAuthorizingRealm {

    public IssueStatesAuthorizingRealm() {
        super("issueState");
    }
}
