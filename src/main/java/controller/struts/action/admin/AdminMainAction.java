package controller.struts.action.admin;

import java.util.*;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import controller.struts.action.common.CustomResults;
import controller.struts.action.common.HandlingExceptionsBaseAction;
import controller.struts.action.common.util.ActionUtils;

import static common.beanFactory.BeanFactoryProvider.*;
import domain.common.exception.AuthorizationFailedException;



public class AdminMainAction extends HandlingExceptionsBaseAction{
    
    private static final Logger LOG = LogManager.getLogger(AdminMainAction.class);
        
    public AdminMainAction() {
        super();
    }
    
    
    @Override
    public String doExecute() throws Exception {
        return ActionUtils.getDecoratedByViewSettingsResult(CustomResults.SUCCESS);
    }
    
    @Override
    protected void logException(Exception e, String message){
        ActionUtils.logException(LOG, e, message);
    }

}
