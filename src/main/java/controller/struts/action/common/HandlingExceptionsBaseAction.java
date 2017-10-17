
package controller.struts.action.common;


import controller.struts.action.common.util.ActionUtils;
import domain.common.exception.AuthorizationFailedException;
import domain.common.exception.EntityWithSuchIdDoesNotExistsBusinessException;
import domain.common.exception.UnchangingContentConstraintViolationException;
import domain.common.exception.ValidationFailedException;
import org.apache.logging.log4j.Logger;

public abstract class HandlingExceptionsBaseAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        try {
            clearMessages();
            getLogger().debug("HandlingExceptionsBaseAction start execute");
            doExecute();
            getLogger().debug("HandlingExceptionsBaseAction executed success " + ActionUtils.getDecoratedByViewSettingsResult(CustomResults.SUCCESS));
            return ActionUtils.getDecoratedByViewSettingsResult(CustomResults.SUCCESS);
        } catch (ValidationFailedException e) {
            getLogger().debug("INVALID_USER_INPUT result", e);
            fillActionErrors(e.getConstraintsViolations());
            addActionError(getText(CustomResults.INVALID_USER_INPUT));
            return ActionUtils.getDecoratedByViewSettingsResult(CustomResults.INVALID_USER_INPUT);
        } catch (EntityWithSuchIdDoesNotExistsBusinessException e) {
            getLogger().debug("RESOURCE_WITH_SUCH_ID_NOT_EXISTS result", e);
            addActionError(getText(CustomResults.RESOURCE_WITH_SUCH_ID_NOT_EXISTS));
            return ActionUtils.getDecoratedByViewSettingsResult(CustomResults.RESOURCE_WITH_SUCH_ID_NOT_EXISTS);
        } catch (AuthorizationFailedException e) {
            getLogger().debug("AUTHORIZATION_ERROR result", e);
            addActionError(getText(CustomResults.AUTHORIZATION_ERROR));
            return ActionUtils.getDecoratedByViewSettingsResult(CustomResults.AUTHORIZATION_ERROR);
        } catch (UnchangingContentConstraintViolationException e) {
            getLogger().debug("UNCHANGING_CONTENT_CONSTRAINT_VIOLATION result", e);
            addActionError(getText(CustomResults.UNCHANGING_CONTENT_CONSTRAINT_VIOLATION));
            return ActionUtils.getDecoratedByViewSettingsResult(CustomResults.UNCHANGING_CONTENT_CONSTRAINT_VIOLATION);
        } catch (Exception e) {
            getLogger().debug("ERROR result");
            getLogger().error("Unknown exception is occured", e);
            addActionError(getText(CustomResults.ERROR));
            return ActionUtils.getDecoratedByViewSettingsResult(CustomResults.ERROR);
        }
    }

    protected abstract void doExecute() throws Exception;

    protected abstract Logger getLogger();


}
