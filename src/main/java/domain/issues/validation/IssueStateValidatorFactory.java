package domain.issues.validation;

import domain.common.CommonValidators;
import domain.issues.IssueState;
import domain.issues.IssueType;
import smartvalidation.entityValidationRule.EntityValidationRule;
import smartvalidation.exception.EntityValidationException;
import smartvalidation.validator.entityValidator.EntityValidator;
import smartvalidation.validator.entityValidator.EntityValidatorFactory;
import smartvalidation.validator.entityValidator.SimpleEntityValidator;
import smartvalidation.validator.entityValidator.ValidationContext;
import smartvalidation.validator.сonstraintValidator.base.ConstraintValidator;

import java.util.ArrayList;
import java.util.List;

import static smartvalidation.validator.сonstraintValidator.ConstraintValidators.isNull;
import static smartvalidation.validator.сonstraintValidator.ConstraintValidators.not;

/**
 * Created by SuslovAI on 04.10.2017.
 */
public class IssueStateValidatorFactory {

    public static class OnCreate implements EntityValidatorFactory<IssueState> {

        private ValidationContext validationContext;

        public OnCreate(ValidationContext validationContext) {
            this.validationContext = validationContext;
        }

        @Override
        public EntityValidator getValidator(IssueState entity) throws EntityValidationException {
            return new SimpleEntityValidator(getIssueStateChangedDataValidationRules(entity));
        }

        private List<EntityValidationRule> getIssueStateChangedDataValidationRules(IssueState entity) throws EntityValidationException {
            try {
                List<EntityValidationRule> entityValidationRules = new ArrayList(2);

                ConstraintValidator commonNamesConstraintValidators = CommonValidators.getSimpleNameConstraintValidator();

                entityValidationRules.add(new EntityValidationRule(validationContext.getPropertiesPrefix() + ".name", entity.getName(), commonNamesConstraintValidators));
                entityValidationRules.add(new EntityValidationRule(validationContext.getPropertiesPrefix() + ".issueType", entity.getIssueType(), not(isNull())));
                return entityValidationRules;
            } catch (Exception e) {
                throw new EntityValidationException("Entity validator initialization exception have occured", e);
            }
        }
    }

    public static class OnUpdate implements EntityValidatorFactory<IssueState> {

        private ValidationContext validationContext;

        public OnUpdate(ValidationContext validationContext) {
            this.validationContext = validationContext;
        }

        @Override
        public EntityValidator getValidator(IssueState entity) throws EntityValidationException {
            return new SimpleEntityValidator(getIssueStateChangedDataValidationRules(entity));
        }

        private List<EntityValidationRule> getIssueStateChangedDataValidationRules(IssueState entity) throws EntityValidationException {
            try {
                List<EntityValidationRule> entityValidationRules = new ArrayList(2);
                ConstraintValidator commonNamesConstraintValidators = CommonValidators.getSimpleNameConstraintValidator();

                entityValidationRules.add(new EntityValidationRule(validationContext.getPropertiesPrefix() + ".id", entity.getId(), not(isNull())));
                entityValidationRules.add(new EntityValidationRule(validationContext.getPropertiesPrefix() + ".name", entity.getName(), commonNamesConstraintValidators));
                entityValidationRules.add(new EntityValidationRule(validationContext.getPropertiesPrefix() + ".issueType", entity.getIssueType(), not(isNull())));

                return entityValidationRules;
            } catch (Exception e) {
                throw new EntityValidationException("Entity validator initialization exception have occured", e);
            }
        }
    }
}
