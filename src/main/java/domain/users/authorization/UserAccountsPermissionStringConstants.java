/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.users.authorization;

import domain.security.authorization.PermissionStringConstants;

/**
 * @author SuslovAI
 */
public class UserAccountsPermissionStringConstants extends PermissionStringConstants {

    public static UserAccountsPermissionStringConstants INSTANCE = new UserAccountsPermissionStringConstants();

    public UserAccountsPermissionStringConstants() {
        super("userAccount");
    }

    public String getFindOneByIdPermission(Long id){
        return getFindOneByIdAttributeNameToValuePermission("id",id);
    }
}
