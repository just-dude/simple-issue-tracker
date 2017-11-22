/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.users.authorization;

import domain.security.UserPrincipal;
import domain.security.authorization.AbstractAuthorizingRealm;
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

public class UserAccountsAuthorizingRealm extends AbstractAuthorizingRealm {

    @Override
    protected Map<UserGroup, SimpleRole> getUserGroupToRoleMap(UserPrincipal userPrincipal) {
        Map<UserGroup, SimpleRole> userGroupToRoleMap = new HashMap<>();
        userGroupToRoleMap.put(UserGroup.Admin,
                new SimpleRole(UserGroup.Admin.name(),
                        new HashSet(
                                Arrays.asList(
                                        new WildcardPermission(
                                                "userAccount:*:*:*")
                                )
                        )
                )
        );
        userGroupToRoleMap.put(UserGroup.CommonUser,
                new SimpleRole(UserGroup.CommonUser.name(),
                        new HashSet(
                                Arrays.asList(
                                        new WildcardPermission(
                                                UserAccountsPermissionStringConstants.INSTANCE.
                                                        getFindOneByIdPermission(userPrincipal.getUserId())
                                        )
                                )
                        )
                )
        );
        return userGroupToRoleMap;
    }

}
