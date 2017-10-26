/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.users.authorization;

import domain.security.authorization.AuthorizingPermissions;

/**
 * @author SuslovAI
 */
public class UserAccountsPermissions extends AuthorizingPermissions {

    public static UserAccountsPermissions INSTANCE = new UserAccountsPermissions();

    public UserAccountsPermissions() {
        super("userAccount");
    }

    public String getFindOneByIdPermission(Long id){
        return getFindOneByIdAttributeNameToValuePermission("id",id);
    }
}
