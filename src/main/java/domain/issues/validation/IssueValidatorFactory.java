package domain.issues.validation;

import domain.common.CommonValidators;
import domain.issues.Issue;
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
 * Created by SuslovAI on 04.10.2017.
 */
public class IssueValidatorFactory {

    public static class OnCreate implements EntityValidatorFactory<Issue> {

        private ValidationContext validationContext;

        public OnCreate(ValidationContext validationContext) {
            this.validationContext = validationContext;
        }

        @Override
        public EntityValidator getValidator(Issue entity) throws EntityValidationException {
            return new SimpleEntityValidator(getIssueChangedDataValidationRules(entity));
        }

        private List<EntityValidationRule> getIssueChangedDataValidationRules(Issue entity) throws EntityValidationException {
            try {
                List<EntityValidationRule> entityValidationRules = new ArrayList(12);

                ConstraintValidator commonNamesConstraintValidators = CommonValidators.getSimpleNameConstraintValidator();

                entityValidationRules.add(new EntityValidationRule(validationContext.getPropertiesPrefix() + ".name", entity.getName(), commonNamesConstraintValidators));
                entityValidationRules.add(new EntityValidationRule(validationContext.getPropertiesPrefix() + ".description", entity.getDescription(),
                        AllOf(not(isNull()),stringLength(not(greaterThan(255))))));
                entityValidationRules.add(new EntityValidationRule(validationContext.getPropertiesPrefix() + ".type", entity.getType(), not(isNull())));
                entityValidationRules.add(new EntityValidationRule(validationContext.getPropertiesPrefix() + ".additionalAttributes", entity.getAdditionalAttributes(), not(isNull())));
                entityValidationRules.add(new EntityValidationRule(validationContext.getPropertiesPrefix() + ".assignee", entity.getAssignee(), not(isNull())));
                entityValidationRules.add(new EntityValidationRule(validationContext.getPropertiesPrefix() + ".proprity", entity.getPriority(), not(isNull())));
                entityValidationRules.add(new EntityValidationRule(validationContext.getPropertiesPrefix() + ".state", entity.getState(), not(isNull())));
                entityValidationRules.add(new EntityValidationRule(validationContext.getPropertiesPrefix() + ".author", entity.getAuthor(), not(isNull())));
                entityValidationRules.add(new EntityValidationRule(validationContext.getPropertiesPrefix() + ".createdDateTime", entity.getCreatedDateTime(), not(isNull())));
                entityValidationRules.add(new EntityValidationRule(validationContext.getPropertiesPrefix() + ".requiredResolvedDateTime", entity.getRequiredResolvedDateTime(), not(isNull())));

                return entityValidationRules;
            } catch (Exception e) {
                throw new EntityValidationException("Entity validator initialization exception have occured", e);
            }
        }
    }

    public static class OnUpdate implements EntityValidatorFactory<Issue> {

        private ValidationContext validationContext;

        public OnUpdate(ValidationContext validationContext) {
            this.validationContext = validationContext;
        }

        @Override
        public EntityValidator getValidator(Issue entity) throws EntityValidationException {
            return new SimpleEntityValidator(getIssueChangedDataValidationRules(entity));
        }

        private List<EntityValidationRule> getIssueChangedDataValidationRules(Issue entity) throws EntityValidationException {
            try {
                List<EntityValidationRule> entityValidationRules = new ArrayList(12);

                ConstraintValidator commonNamesConstraintValidators = CommonValidators.getSimpleNameConstraintValidator();

                entityValidationRules.add(new EntityValidationRule(validationContext.getPropertiesPrefix() + ".id", entity.getId(), not(isNull())));
                entityValidationRules.add(new EntityValidationRule(validationContext.getPropertiesPrefix() + ".name", entity.getName(), commonNamesConstraintValidators));
                entityValidationRules.add(new EntityValidationRule(validationContext.getPropertiesPrefix() + ".description", entity.getDescription(),
                        AllOf(not(isNull()),stringLength(not(greaterThan(255))))));
                entityValidationRules.add(new EntityValidationRule(validationContext.getPropertiesPrefix() + ".type", entity.getType(), not(isNull())));
                entityValidationRules.add(new EntityValidationRule(validationContext.getPropertiesPrefix() + ".additionalAttributes", entity.getAdditionalAttributes(), not(isNull())));
                entityValidationRules.add(new EntityValidationRule(validationContext.getPropertiesPrefix() + ".assignee", entity.getAssignee(), not(isNull())));
                entityValidationRules.add(new EntityValidationRule(validationContext.getPropertiesPrefix() + ".proprity", entity.getPriority(), not(isNull())));
                entityValidationRules.add(new EntityValidationRule(validationContext.getPropertiesPrefix() + ".state", entity.getState(), not(isNull())));
                entityValidationRules.add(new EntityValidationRule(validationContext.getPropertiesPrefix() + ".author", entity.getAuthor(), not(isNull())));
                entityValidationRules.add(new EntityValidationRule(validationContext.getPropertiesPrefix() + ".createdDateTime", entity.getCreatedDateTime(), not(isNull())));
                entityValidationRules.add(new EntityValidationRule(validationContext.getPropertiesPrefix() + ".requiredResolvedDateTime", entity.getRequiredResolvedDateTime(), not(isNull())));

                return entityValidationRules;
            } catch (Exception e) {
                throw new EntityValidationException("Entity validator initialization exception have occured", e);
            }
        }
    }
}
