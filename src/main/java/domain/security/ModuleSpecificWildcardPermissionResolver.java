/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.security;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;

/**
 * @author SuslovAI
 */
public class ModuleSpecificWildcardPermissionResolver implements PermissionResolver {


    @Override
    public Permission resolvePermission(String permissionString) {
        return new ModuleSpecificWildcardPermission(permissionString);
    }


}
