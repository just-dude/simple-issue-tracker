/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.common.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import domain.common.exception.BusinessException;
import domain.common.exception.ValidationFailedException;
import smartvalidation.validator.entityValidator.EntityValidator;
import smartvalidation.validator.entityValidator.EntityValidatorFactory;
import static common.beanFactory.BeanFactoryProvider.*;


public class ValidationUtils {
    
    private static final Logger LOG = LogManager.getLogger(ValidationUtils.class);
    
    public static void ifInvalidThrowValidationFailedException(Object entity, String entityValidatorFactoryBeanName) throws BusinessException {
        try{
            EntityValidator validator =getValidatorFactory(entityValidatorFactoryBeanName).getValidator(entity);
            if(!validator.isValid()){
                throw new ValidationFailedException(validator.getConstraintViolations());
            }
        } catch(Exception e){
            throw BusinessExceptionHandleUtils.handleExceptionAndReturnBusinessException(e, LOG, entity, "on validation");
        }        
    }
    
    private static EntityValidatorFactory getValidatorFactory(String entityValidatorFactoryBeanName) throws BusinessException {
        return (EntityValidatorFactory)getBeanFactory().getBean(entityValidatorFactoryBeanName);//example: UserProfileValidatorFactory;
    }
}
