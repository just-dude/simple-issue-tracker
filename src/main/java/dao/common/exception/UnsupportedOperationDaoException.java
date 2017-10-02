package dao.common.exception;

/**
 * Created by SuslovAI on 24.09.2017.
 */
public class UnsupportedOperationDaoException extends DaoException {

    private static final String MESSAGE_FORMAT = "Operation '%s' unsupprted by this class";

    public UnsupportedOperationDaoException(String operationName) {
        super(String.format(MESSAGE_FORMAT, operationName));
    }
}
