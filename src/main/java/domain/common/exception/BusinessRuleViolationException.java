package domain.common.exception;

/**
 * Created by SuslovAI on 01.12.2016.
 */
public class BusinessRuleViolationException extends BusinessException {

    public BusinessRuleViolationException() {
    }

    public BusinessRuleViolationException(String message) {
        super(message);
    }
}
