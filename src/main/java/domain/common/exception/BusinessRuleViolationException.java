package domain.common.exception;

/**
 * Created by SuslovAI on 01.12.2016.
 */
public class BusinessRuleViolationException extends BusinessException {

    public BusinessRuleViolationException(String message) {
        super(message);
    }

    public BusinessRuleViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
