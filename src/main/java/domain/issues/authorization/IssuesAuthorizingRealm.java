/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.issues.authorization;

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

public class IssuesAuthorizingRealm extends AbstractAuthorizingRealm {

    @Override
    protected Map<UserGroup, SimpleRole> getUserGroupToRoleMap(UserPrincipal userPrincipal) {
        Map<UserGroup, SimpleRole> userGroupToRoleMap = new HashMap<>();
        userGroupToRoleMap.put(UserGroup.Admin,
                new SimpleRole(UserGroup.Admin.name(),
                        new HashSet(
                                Arrays.asList(
                                        new WildcardPermission(
                                                "issue:*:*:*")
                                )
                        )
                )
        );
        userGroupToRoleMap.put(UserGroup.CommonUser,
                new SimpleRole(UserGroup.CommonUser.name(),
                        new HashSet(
                                Arrays.asList(
                                        new WildcardPermission(
                                        IssuesPermissions.INSTANCE.getSavePermission()
                                        ),
                                        new WildcardPermission(
                                                IssuesPermissions.INSTANCE.getGoIntoStatePermission(userPrincipal.getUserId())
                                        ),
                                        new WildcardPermission(
                                                IssuesPermissions.INSTANCE.getChangeRequiredResolvedDateTimePermission(userPrincipal.getUserId())
                                        ),
                                        new WildcardPermission(
                                                "issue:findOneById,findAllByAuthorId,findAllByAssigneeId:authorId,assigneeId:"
                                                        + userPrincipal.getUserId())
                                )
                        )
                )
        );
        return userGroupToRoleMap;
    }

}
