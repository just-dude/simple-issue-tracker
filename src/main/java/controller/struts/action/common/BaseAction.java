
package controller.struts.action.common;


import com.opensymphony.xwork2.interceptor.ValidationWorkflowAware;
import java.util.List;
import controller.struts.action.common.util.ActionUtils;

import smartvalidation.constraintViolation.ConstraintViolation;
import smartvalidation.constraintViolation.PropertyConstraintViolation;
import domain.common.exception.BusinessException;
import domain.common.exception.BusinessRuleViolationException;
import domain.common.exception.UnchangingContentConstraintViolationException;
import domain.common.exception.ValidationFailedException;
import smartvalidation.constraintViolation.EntityConstraintViolation;

public abstract class BaseAction extends com.opensymphony.xwork2.ActionSupport implements ValidationWorkflowAware{
    
    protected void fillActionErrors(List<ConstraintViolation> constraintsViolations){
        for(ConstraintViolation cv: constraintsViolations){
            if(cv instanceof PropertyConstraintViolation){
                PropertyConstraintViolation pcv = (PropertyConstraintViolation)cv;
                this.addFieldError(pcv.getPropertyPath(),pcv.getConstraintDescription().getConstraintFullName());
                if(pcv.getConstraintDescription().getConstraintParameters().length>0){
                    for(String parameter:pcv.getConstraintDescription().getConstraintParameters()){
                        this.addFieldError(pcv.getPropertyPath(), parameter);
                    }
                }
            }
            if(cv instanceof EntityConstraintViolation){
                this.addActionError(((EntityConstraintViolation)cv).getConstraintDescription().getConstraintFullName());
            }
        }
    }
    
    @Override
    public String getInputResultName() {
        return ActionUtils.getDecoratedByViewSettingsResult(CustomResults.INVALID_USER_INPUT);
    }
}
