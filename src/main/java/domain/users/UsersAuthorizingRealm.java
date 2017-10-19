/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.users;

import domain.security.AbstractAuthorizingRealm;
import domain.security.UserPrincipal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.SimpleRole;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.subject.PrincipalCollection;
import ru.zabgu.service.common.beanFactory.BeanFactoryProvider;
import ru.zabgu.service.common.business.reader.TwopartPKBasedReaderService;
import ru.zabgu.service.concreteService.users.userGroups.model.StaticUserGroups;
import ru.zabgu.service.concreteService.users.userGroupsAndUserRolesR10ps.model.UserGroupAndUserRoleOnePKToOnePKR10p;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author SuslovAI
 */

public class UsersAuthorizingRealm extends AbstractAuthorizingRealm {

    private static final Logger LOG = LogManager.getLogger(UsersAuthorizingRealm.class.getName());

    private Map<Long, Long> groupIdToPermissionsMap = new HashMap<>();
    private static final Long UNASSIGNED_GROUP_TO_ROLE_USER_ROLE_ID = UsersRole.USERS_UNKNOWING.getId();
    private static final Long UNAUTHENTICATED_USER_ROLE_ID = UsersRole.UNAUTHENTICATED_USERS_UNKNOWING.getId();

    public UsersAuthorizingRealm() {
        groupIdToPermissionsMap.put(StaticUserGroups.ADMIN.getId(), UsersRole.USERS_ADMIN.getId());
        groupIdToPermissionsMap.put(StaticUserGroups.ADVANCED_EDITOR.getId(), UsersRole.USERS_ADVANCED_EDITOR.getId());
        groupIdToPermissionsMap.put(StaticUserGroups.EDITOR.getId(), UsersRole.USERS_EDITOR.getId());
        groupIdToPermissionsMap.put(StaticUserGroups.VISITOR.getId(), UsersRole.USERS_UNKNOWING.getId());
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
        UsersRole currentUsersRole = null;
        UserPrincipal userPrincipal = null;
        LOG.debug(String.format("principals: %s ", pc.asList()));
        if (!pc.isEmpty()) {
            userPrincipal = pc.oneByType(UserPrincipal.class);
            if (getGroupIdsAndRoleIdsRelationships().containsKey(userPrincipal.getUserGroupId())) {
                currentUsersRole = UsersRole.valueOf(getGroupIdsAndRoleIdsRelationships().get(userPrincipal.getUserGroupId()));
            } else {
                currentUsersRole = UsersRole.valueOf(UNASSIGNED_GROUP_TO_ROLE_USER_ROLE_ID);
            }
        } else {
            currentUsersRole = UsersRole.valueOf(UNAUTHENTICATED_USER_ROLE_ID);
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(new HashSet(Arrays.asList(currentUsersRole.getName())));
        info.addObjectPermissions(
                getRoleIdToRoleMap(userPrincipal.getUserId()).get(currentUsersRole.getId()).getPermissions()
        );
        LOG.debug(String.format("result info: roles %s, permissions %s; by principals: %s ", info.getRoles(), info.getObjectPermissions(), pc.asList()));
        return info;
    }

    public Map<Long, Long> getGroupIdsAndRoleIdsRelationships() {
        TwopartPKBasedReaderService rs = (TwopartPKBasedReaderService) BeanFactoryProvider.getBeanFactory().getBean("UserGroupAndUserRolesOnePKToOnePKsRelationshipsReaderService");
        List<UserGroupAndUserRoleOnePKToOnePKR10p> userGroupsAnduserRolesRelationships = rs.findAllPages();
        return userGroupsAnduserRolesRelationships.stream().collect(
                Collectors.toMap(UserGroupAndUserRoleOnePKToOnePKR10p::getFirstParticipantPK,
                        UserGroupAndUserRoleOnePKToOnePKR10p::getSecondParticipantPK));

    }

    protected Map<Long, SimpleRole> getRoleIdToRoleMap(Long userId) {
        Map<Long, SimpleRole> roleIdToRoleMap = new HashMap<>();
        roleIdToRoleMap.put(UsersRole.USERS_ADMIN.getId(),
                new SimpleRole(UsersRole.USERS_ADMIN.getName(),
                        new HashSet(
                                Arrays.asList(
                                        new WildcardPermission(
                                                "users:users:addVisitor,changeUserAccountAndProfile,changeUserGroupRelationship,remove,browse:*"),
                                        new WildcardPermission("users:userGroups:change,browse:*"),
                                        new WildcardPermission("users:userGroupAndUserRoleR10ps:change,browse:*")
                                )
                        )
                )
        );
        roleIdToRoleMap.put(UsersRole.USERS_ADVANCED_EDITOR.getId(),
                new SimpleRole(UsersRole.USERS_ADVANCED_EDITOR.getName(),
                        new HashSet(
                                Arrays.asList(
                                        new WildcardPermission("users:users:addVisitor,changeUserAccountAndProfile,remove,browse:*"),
                                        new WildcardPermission("users:userGroups:change,browse:*")
                                )
                        )
                )
        );
        roleIdToRoleMap.put(UsersRole.USERS_EDITOR.getId(),
                new SimpleRole(UsersRole.USERS_EDITOR.getName(),
                        new HashSet(
                                Arrays.asList(
                                        new WildcardPermission("users:users:addVisitor,changeUserAccountAndProfile,browse:*")
                                )
                        )
                )
        );
        roleIdToRoleMap.put(UsersRole.USERS_READER.getId(),
                new SimpleRole(UsersRole.USERS_READER.getName(),
                        new HashSet(
                                Arrays.asList(
                                        new WildcardPermission("users:users:addVisitor,browse:*")
                                )
                        )
                )
        );
        roleIdToRoleMap.put(UsersRole.USERS_UNKNOWING.getId(),
                new SimpleRole(UsersRole.USERS_UNKNOWING.getName(),
                        new HashSet(
                                Arrays.asList(
                                        new WildcardPermission("users:users:addVisitor:*"),
                                        new WildcardPermission("users:users:browse:" + userId)
                                )
                        )
                )
        );
        roleIdToRoleMap.put(UsersRole.UNAUTHENTICATED_USERS_UNKNOWING.getId(),
                new SimpleRole(UsersRole.UNAUTHENTICATED_USERS_UNKNOWING.getName(),
                        new HashSet(
                                Arrays.asList(
                                        new WildcardPermission("users:users:addVisitor:*")
                                )
                        )
                )
        );
        return roleIdToRoleMap;
    }

    /**
     * @author SuslovAI
     */
    public static enum UsersRole {
        USERS_ADMIN(1L, "Users admin"),
        USERS_ADVANCED_EDITOR(2L, "Users advanced editor"),
        USERS_EDITOR(3L, "Users editor"),
        USERS_READER(4L, "Users reader"),
        USERS_UNKNOWING(5L, "Users unknowing"),
        UNAUTHENTICATED_USERS_UNKNOWING(6L, "Unauthenticated users unknowing");

        private final Long id;
        private final String name;

        private UsersRole(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public static UsersRole valueOf(long id) {
            switch ((int) id) {
                case 1:
                    return USERS_ADMIN;
                case 2:
                    return USERS_ADVANCED_EDITOR;
                case 3:
                    return USERS_EDITOR;
                case 4:
                    return USERS_READER;
                case 5:
                    return USERS_UNKNOWING;
                case 6:
                    return UNAUTHENTICATED_USERS_UNKNOWING;
                default:
                    return UNAUTHENTICATED_USERS_UNKNOWING;
            }
        }
    }
}
