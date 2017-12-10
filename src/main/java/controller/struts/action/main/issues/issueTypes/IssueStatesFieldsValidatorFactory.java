package controller.struts.action.main.issues.issueTypes;

import domain.common.CommonValidators;
import org.apache.commons.lang3.StringUtils;
import smartvalidation.entityValidationRule.EntityValidationRule;
import smartvalidation.exception.EntityValidationException;
import smartvalidation.validator.entityValidator.EntityValidator;
import smartvalidation.validator.entityValidator.EntityValidatorFactory;
import smartvalidation.validator.entityValidator.SimpleEntityValidator;
import smartvalidation.validator.entityValidator.ValidationContext;
import smartvalidation.validator.сonstraintValidator.base.ConstraintValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static smartvalidation.validator.сonstraintValidator.ConstraintValidators.*;

/**
 * Created by Администратор on 09.12.2017.
 */
public class IssueStatesFieldsValidatorFactory implements EntityValidatorFactory<WriteIssueTypeAction.IssueStatesFields> {

    private ValidationContext validationContext;

    public IssueStatesFieldsValidatorFactory(ValidationContext validationContext) {
        this.validationContext = validationContext;
    }

    @Override
    public EntityValidator getValidator(WriteIssueTypeAction.IssueStatesFields entity) throws EntityValidationException {
        return new SimpleEntityValidator(getIssueStatesFieldsChangedDataValidationRules(entity));
    }

    private List<EntityValidationRule> getIssueStatesFieldsChangedDataValidationRules(WriteIssueTypeAction.IssueStatesFields entity) throws EntityValidationException {
        try {
            final String delimeter = ",";
            List<EntityValidationRule> entityValidationRules = new ArrayList();

            ConstraintValidator commonNamesConstraintValidators = CommonValidators.getSimpleNameConstraintValidator();

            entityValidationRules.add(
                    new EntityValidationRule(validationContext.getPropertiesPrefix() + ".initialStateName",
                            entity.getInitialStateName(), commonNamesConstraintValidators));

            entityValidationRules.add(
                    new EntityValidationRule(validationContext.getPropertiesPrefix() + ".transitionsStatesNames",
                            entity.getTransitionsStatesNames(), AllOf(not(isNull()))));
            if(entity.getTransitionsStatesNames()!=null && entity.getTransitionsStatesNames().trim()!=""){
                String[] transitionsStatesNames = StringUtils.split(entity.getTransitionsStatesNames(),delimeter);
                for(int i=0;i<transitionsStatesNames.length;i++){
                    entityValidationRules.add(
                            new EntityValidationRule(validationContext.getPropertiesPrefix() + ".transitionsStatesNames["+i+"]",
                                    transitionsStatesNames[i], commonNamesConstraintValidators));
                }
            }

            entityValidationRules.add(
                    new EntityValidationRule(validationContext.getPropertiesPrefix() + ".standartFinishStatesNames",
                            entity.getStandartFinishStatesNames(), AllOf(not(isNull()),not(blankString()))));
            String[] standartFinishStatesNames = StringUtils.split(entity.getStandartFinishStatesNames(),delimeter);
            for(int i=0;i<standartFinishStatesNames.length;i++){
                entityValidationRules.add(
                        new EntityValidationRule(validationContext.getPropertiesPrefix() + ".standartFinishStatesNames["+i+"]",
                                standartFinishStatesNames[i], commonNamesConstraintValidators));
            }

            entityValidationRules.add(
                    new EntityValidationRule(validationContext.getPropertiesPrefix() + ".nonStandartFinishStatesNames",
                            entity.getNonStandartFinishStatesNames(), AllOf(not(isNull()))));
            if(entity.getTransitionsStatesNames()!=null && entity.getNonStandartFinishStatesNames().trim()!=""){
                String[] nonStandartFinishStatesNames = StringUtils.split(entity.getNonStandartFinishStatesNames(),delimeter);
                for(int i=0;i<nonStandartFinishStatesNames.length;i++){
                    entityValidationRules.add(
                            new EntityValidationRule(validationContext.getPropertiesPrefix() + ".nonStandartFinishStatesNames["+i+"]",
                                    nonStandartFinishStatesNames[i], commonNamesConstraintValidators));
                }
            }

            return entityValidationRules;
        } catch (Exception e) {
            throw new EntityValidationException("Entity validator initialization exception have occured", e);
        }
    }
}