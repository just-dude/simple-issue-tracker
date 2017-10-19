/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.users;

/**
 * @author SuslovAI
 */
public class UsersAuthorizingPermissions {

    public static final String CHANGE_USER_ACCOUNT_AND_PROFILE_USERS_PERMISSION = "users:users:changeUserAccountAndProfile:*";
    public static final String CHANGE_USER_GROUP_RELATIONSHIP_USERS_PERMISSION = "users:users:changeUserGroupRelationship:*";
    public static final String REMOVE_USERS_PERMISSION = "users:users:remove:*";
    public static final String ADD_VISITOR_USERS_PERMISSION = "users:users:addVisitor:*";
    private static final String BROWSE_SELF_USES_PERMISSION = "users:users:browse:%s";
    public static final String BROWSE_ALL_USERS_PERMISSION = "users:users:browse:*";

    public static final String CHANGE_USERGROUPS_PERMISSION = "users:userGroups:change:*";
    public static final String BROWSE_USERGROUPS_PERMISSION = "users:userGroups:browse:*";

    public static final String CHANGE_USERSROLESANDGROUPSRELATIONSHIP_PERMISSION = "users:usersRolesAndGroupsRelationship:change:*";
    public static final String BROWSE_USERSROLESANDGROUPSRELATIONSHIP_PERMISSION = "users:usersRolesAndGroupsRelationship:browse:*";


    public static String getBrowseSelfUserPermission(Long selfId) {
        return String.format(BROWSE_SELF_USES_PERMISSION, selfId);
    }

}
