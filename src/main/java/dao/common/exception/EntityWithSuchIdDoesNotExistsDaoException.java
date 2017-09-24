package dao.common.exception;

/**
 * Created by suslovai on 15.09.2017.
 */
public class EntityWithSuchIdDoesNotExistsDaoException extends DaoException {

    private static final String MESSAGE_FORMAT = "Entity with such id - %s not exists";

    public EntityWithSuchIdDoesNotExistsDaoException(Object id) {
        super(String.format(MESSAGE_FORMAT, id));
    }

    public EntityWithSuchIdDoesNotExistsDaoException(Object id, Throwable cause) {
        super(String.format(MESSAGE_FORMAT, id), cause);
    }
}
