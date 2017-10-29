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
public class IssuesPermissions extends AuthorizingPermissions {

    private static final String FIND_ALL_BY_ATTRIBUTE_PERMISSION_FORMAT = "%s:findAllById%s:%s:%s";

    public static IssuesPermissions INSTANCE = new IssuesPermissions();

    public IssuesPermissions() {
        super("issue");
    }

    @Override
    public String getFindOneByIdPermission(Long userId){
        return getFindOneByIdAttributeNameToValuePermission("assigneeId,authorId",userId);
    }

    public String getGoIntoStatePermission(Long userId){
        return domainObjectName+":goIntoState:assigneeId:"+userId;
    }

    public String getChangeRequiredResolvedDateTimePermission(Long userId){
        return domainObjectName+":changeRequiredResolvedDateTime:authorId:"+userId;
    }

    public String getFindAllByAuthorPermission(Long authorId){
        return String.format(FIND_ALL_BY_ATTRIBUTE_PERMISSION_FORMAT,domainObjectName,"AuthorId","authorId",authorId);
    }

    public String getFindAllByAssigneePermission(Long assigneeId){
        return String.format(FIND_ALL_BY_ATTRIBUTE_PERMISSION_FORMAT,domainObjectName,"AssigneeId","assigneeId",assigneeId);
    }
}
