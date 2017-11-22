/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.issues.authorization;

import domain.security.authorization.PermissionStringConstants;

/**
 * @author SuslovAI
 */
public class IssueTypesPermissionStringConstants extends PermissionStringConstants {

    public static IssueTypesPermissionStringConstants INSTANCE = new IssueTypesPermissionStringConstants();

    public IssueTypesPermissionStringConstants() {
        super("issueTypes");
    }
}
