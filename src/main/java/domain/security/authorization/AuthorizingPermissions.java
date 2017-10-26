package domain.security.authorization;

/**
 * Created by Администратор on 24.10.2017.
 */
public class AuthorizingPermissions {

    private static final String FIND_ONE_BY_ID_PERMISSION_FORMAT = "%s:findOneById:%s:%s";

    protected String domainObjectName;
    
    public AuthorizingPermissions(String domainObjectName){
        this.domainObjectName=domainObjectName;
    }

    public String getFindOneByIdAttributeNameToValuePermission(String attributeName, Long attributeValue) {
        return String.format(FIND_ONE_BY_ID_PERMISSION_FORMAT,this.domainObjectName,attributeName, attributeValue);
    }

    public String getFindOneByIdPermission(Long id){
        return getFindOneByIdAttributeNameToValuePermission("id",id);
    }

    public String getSavePermission(){
        return this.domainObjectName+":save:*";
    }
    public String getRemovePermission(){
        return this.domainObjectName+":remove:*";
    }
    public String getFindAllPermission(){
        return this.domainObjectName+":findAll:*";
    }
}
