package dao.common.exception;

/**
 * Created by SuslovAI on 15.10.2017.
 */
public class DataIntegrityViolationDaoException extends DaoException {

    private static final String MESSAGE = "Data integrity violation has occured. Can't remove entity, because other entities have references to this entity.";

    private Object id;

    public DataIntegrityViolationDaoException() {
        super(MESSAGE);
    }

    public DataIntegrityViolationDaoException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
