package controller.struts.action.admin;

import controller.struts.action.common.HandlingExceptionsBaseAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AdminMainAction extends HandlingExceptionsBaseAction {

    protected static final Logger LOG = LogManager.getLogger(AdminMainAction.class);

    public AdminMainAction() {
        super();
    }


    @Override
    public void doExecute() throws Exception {
    }

    @Override
    protected Logger getLogger() {
        return LOG;
    }

}
