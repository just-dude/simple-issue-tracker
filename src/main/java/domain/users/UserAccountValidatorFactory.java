package domain.users;

import smartvalidation.entityValidationRule.EntityValidationRule;
import smartvalidation.exception.EntityValidationException;
import smartvalidation.validator.entityValidator.EntityValidator;
import smartvalidation.validator.entityValidator.EntityValidatorFactory;
import smartvalidation.validator.entityValidator.SimpleEntityValidator;
import smartvalidation.validator.entityValidator.ValidationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SuslovAI on 03.10.2017.
 */
public class UserAccountValidatorFactory implements EntityValidatorFactory<UserAccount> {

    private ValidationContext validationContext;

    public UserAccountValidatorFactory(ValidationContext validationContext) {
        this.validationContext = validationContext;
    }

    @Override
    public EntityValidator getValidator(UserAccount entity) throws EntityValidationException {
        return new SimpleEntityValidator(getUserAccountChangedDataValidationRules(entity));
    }

    private List<EntityValidationRule> getUserAccountChangedDataValidationRules(UserAccount entity) throws EntityValidationException {
        try {
            List<EntityValidationRule> entityValidationRules = new ArrayList(2);
//            Pattern loginRexpPattern = Pattern.compile("^[A-Za-z0-9\\-_]+$", Pattern.UNICODE_CHARACTER_CLASS);
//            ConstraintValidator loginValidator = AllOf(not(isNull()), not(blankString()), stringLength(lessThan(100)),
//                    matchesRegularExpression(loginRexpPattern, "matchToLoginPattern"));
//            entityValidationRules.add(new EntityValidationRule(validationContext.getPropertiesPrefix() + ".login", entity.getLogin(), loginValidator));
//            entityValidationRules.add(UserAccountValidatorsFactories.getPasswordValidationRule(entity.getPassword(), validationContext.getPropertiesPrefix()));
            return entityValidationRules;
        } catch (Exception e) {
            throw new EntityValidationException("Entity validator initialization exception have occured", e);
        }
    }

}
