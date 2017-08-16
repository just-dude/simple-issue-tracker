package controller.struts.action.main;

import controller.struts.action.admin.*;
import static com.mchange.v2.c3p0.impl.C3P0Defaults.user;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import controller.struts.action.common.CustomResults;
import controller.struts.action.common.HandlingExceptionsBaseAction;
import controller.struts.action.common.util.ActionUtils;

import static common.beanFactory.BeanFactoryProvider.*;


public class IndexAction extends HandlingExceptionsBaseAction{
    
    private static final Logger LOG = LogManager.getLogger(IndexAction.class);
        
    public IndexAction() {
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
