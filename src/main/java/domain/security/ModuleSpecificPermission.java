/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.security;

import org.apache.shiro.authz.Permission;

/**
 * @author SuslovAI
 */
public interface ModuleSpecificPermission extends Permission {

    String getModuleSpecificName();
}
