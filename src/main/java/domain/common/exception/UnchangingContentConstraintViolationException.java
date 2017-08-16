package domain.common.exception;

/**
 * Created by SuslovAI on 01.12.2016.
 */
public class UnchangingContentConstraintViolationException extends BusinessRuleViolationException {

    private int currentUnchangingContentElementId;
    private int unchangingContentElementsCount;

    public UnchangingContentConstraintViolationException(int currentUnchangingContentElementId, int unchangingContentElementsCount) {
        super("Can't change unchanging content element - "+currentUnchangingContentElementId+" of "+unchangingContentElementsCount);
        this.currentUnchangingContentElementId = currentUnchangingContentElementId;
        this.unchangingContentElementsCount = unchangingContentElementsCount;
    }
    
    public Integer getCurrentUnchangingContentElementId() {
        return currentUnchangingContentElementId;
    }

    public Integer getUnchangingContentElementsCount() {
        return unchangingContentElementsCount;
    }
    
    
}
