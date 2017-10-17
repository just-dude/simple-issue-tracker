package controller.struts.action.main;

import controller.struts.action.common.HandlingExceptionsBaseAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class IndexAction extends HandlingExceptionsBaseAction {

    protected static final Logger LOG = LogManager.getLogger(IndexAction.class);

    public IndexAction() {
        super();
    }

    @Override
    public void doExecute() throws Exception {
        LOG.info("IndexAction is executing");
    }

    @Override
    protected Logger getLogger() {
        return LOG;
    }
}
