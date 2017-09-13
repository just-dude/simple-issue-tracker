
package controller.struts.action.common;


import controller.struts.action.common.util.ActionUtils;
import domain.common.exception.AuthorizationFailedException;
import domain.common.exception.UnchangingContentConstraintViolationException;
import domain.common.exception.ValidationFailedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class HandlingExceptionsBaseAction extends BaseAction {

    protected static final Logger LOG = LogManager.getLogger(HandlingExceptionsBaseAction.class);

    @Override
    public String execute() throws Exception {
        try {
            LOG.debug("HandlingExceptionsBaseAction start execute");
            return doExecute();
        } catch (ValidationFailedException e) {
            LOG.debug("INVALID_USER_INPUT result", e);
            fillActionErrors(e.getConstraintsViolations());
            getActionErrors().add(getText(CustomResults.INVALID_USER_INPUT));
            return ActionUtils.getDecoratedByViewSettingsResult(CustomResults.INVALID_USER_INPUT);
        } catch (AuthorizationFailedException e) {
            LOG.debug("AUTHORIZATION_ERROR result", e);
            getActionErrors().add(getText(CustomResults.AUTHORIZATION_ERROR));
            return ActionUtils.getDecoratedByViewSettingsResult(CustomResults.AUTHORIZATION_ERROR);
        } catch (UnchangingContentConstraintViolationException e) {
            LOG.debug("UNCHANGING_CONTENT_CONSTRAINT_VIOLATION result", e);
            getActionErrors().add(getText(CustomResults.UNCHANGING_CONTENT_CONSTRAINT_VIOLATION));
            return ActionUtils.getDecoratedByViewSettingsResult(CustomResults.UNCHANGING_CONTENT_CONSTRAINT_VIOLATION);
        } catch (Exception e) {
            LOG.debug("ERROR result");
            logException(e, "Unknown exception is occured");
            getActionErrors().add(getText(CustomResults.ERROR));
            return ActionUtils.getDecoratedByViewSettingsResult(CustomResults.ERROR);
        }
    }

    protected abstract String doExecute() throws Exception;

    protected abstract void logException(Exception e, String message);

}
