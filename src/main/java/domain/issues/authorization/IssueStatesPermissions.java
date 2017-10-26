/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.issues.authorization;

import domain.security.authorization.AuthorizingPermissions;

/**
 * @author SuslovAI
 */
public class IssueStatesPermissions extends AuthorizingPermissions {

    private static final String FIND_ALL_BY_ATTRIBUTE_PERMISSION_FORMAT = "%s:findAllById%s:%s:%s";

    public static IssueStatesPermissions INSTANCE = new IssueStatesPermissions();

    public IssueStatesPermissions() {
        super("issueState");
    }

}
