/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.security;

import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * @author SuslovAI
 */
public class ModuleSpecificWildcardPermission extends WildcardPermission implements ModuleSpecificPermission {

    public ModuleSpecificWildcardPermission() {
    }

    public ModuleSpecificWildcardPermission(String wildcardString) {
        super(wildcardString);
    }

    public ModuleSpecificWildcardPermission(String wildcardString, boolean caseSensitive) {
        super(wildcardString, caseSensitive);
    }


    @Override
    public String getModuleSpecificName() {
        return this.getParts().get(0).iterator().next();
    }

}
