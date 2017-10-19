/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.security;

import domain.common.exception.AuthorizationFailedException;

/**
 * @author SuslovAI
 */
public interface AuthorizingService {


    public boolean isPermitted(String permission);


    public boolean[] isPermitted(String... permissions);


    public boolean isPermittedAll(String... permissions);


    public void checkPermission(String permission) throws AuthorizationFailedException;


    public void checkPermissions(String... permissions) throws AuthorizationFailedException;


    public boolean hasRole(String roleName);

}
