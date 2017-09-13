
package controller.struts.action.common;


import controller.struts.action.common.util.ActionUtils;
import domain.common.exception.AuthorizationFailedException;
import domain.common.exception.BusinessException;


public abstract class FormProviderHandlingExceptionsBaseAction extends HandlingExceptionsBaseAction {

    protected int prevActionSuccess;

    @Override
    public String input() throws Exception {
        try {
            LOG.debug("FormProviderHandlingExceptionsBaseAction input start execute");
            return doInput();
        } catch (AuthorizationFailedException e) {
            LOG.debug("authorization-error result");
            getActionErrors().add(getText(CustomResults.AUTHORIZATION_ERROR));
            return ActionUtils.getDecoratedByViewSettingsResult(CustomResults.AUTHORIZATION_ERROR);
        } catch (BusinessException e) {
            LOG.debug("ERROR result");
            logException(e, "Exception is occured");
            getActionErrors().add(getText(CustomResults.ERROR));
            return ActionUtils.getDecoratedByViewSettingsResult(CustomResults.ERROR);
        } catch (Exception e) {
            LOG.debug("ERROR result");
            logException(e, "Unknown exception is occured");
            getActionErrors().add(getText(CustomResults.ERROR));
            return ActionUtils.getDecoratedByViewSettingsResult(CustomResults.ERROR);
        }
    }

    protected abstract String doInput() throws Exception;

    public int getPrevActionSuccess() {
        return prevActionSuccess;
    }

    public void setPrevActionSuccess(int prevActionSuccess) {
        this.prevActionSuccess = prevActionSuccess;
    }


}
