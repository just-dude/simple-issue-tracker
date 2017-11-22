package controller.struts.action.authentication;

import controller.struts.action.common.FormProviderHandlingExceptionsBaseAction;
import domain.security.SecuritySubjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by SuslovAI on 18.10.2017.
 */
public class LogoutAction extends FormProviderHandlingExceptionsBaseAction {

    protected static final Logger LOG = LogManager.getLogger(LogoutAction.class);

    @Override
    protected void doExecute() throws Exception {
        SecuritySubjectUtils.logout();
    }

    @Override
    protected Logger getLogger() {
        return LOG;
    }
}
