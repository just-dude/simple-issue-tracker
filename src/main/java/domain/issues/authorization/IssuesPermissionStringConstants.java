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
public class IssuesPermissionStringConstants extends PermissionStringConstants {

    private static final String FIND_ALL_BY_ATTRIBUTE_PERMISSION_FORMAT = "%s:findAllById%s:%s:%s";

    public static IssuesPermissionStringConstants INSTANCE = new IssuesPermissionStringConstants();

    public IssuesPermissionStringConstants() {
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
