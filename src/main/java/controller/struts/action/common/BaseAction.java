
package controller.struts.action.common;


import com.opensymphony.xwork2.interceptor.ValidationWorkflowAware;
import controller.struts.action.common.util.ActionUtils;
import smartvalidation.constraintViolation.ConstraintViolation;
import smartvalidation.constraintViolation.EntityConstraintViolation;
import smartvalidation.constraintViolation.PropertyConstraintViolation;

import java.util.List;

public abstract class BaseAction extends com.opensymphony.xwork2.ActionSupport implements ValidationWorkflowAware {

    protected void fillActionErrors(List<ConstraintViolation> constraintsViolations) {
        for (ConstraintViolation cv : constraintsViolations) {
            if (cv instanceof PropertyConstraintViolation) {
                PropertyConstraintViolation pcv = (PropertyConstraintViolation) cv;
                this.addFieldError(pcv.getPropertyPath(),
                        getText(
                                pcv.getConstraintDescription().getConstraintFullName(),
                                pcv.getConstraintDescription().getConstraintFullName(),
                                pcv.getConstraintDescription().getConstraintParameters()
                        )
                );
            }
            if (cv instanceof EntityConstraintViolation) {
                this.addActionError(((EntityConstraintViolation) cv).getConstraintDescription().getConstraintFullName());
            }
        }
    }

    @Override
    public String getInputResultName() {
        return ActionUtils.getDecoratedByViewSettingsResult(CustomResults.INVALID_USER_INPUT);
    }
}
