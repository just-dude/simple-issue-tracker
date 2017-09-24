
package dao.common.exception;


import common.exception.ApplicationException;

import java.util.Map;

public class DaoException extends ApplicationException {


    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, String contextName) {
        super(message, contextName);
    }

    public DaoException(String message, String contextName, Object... contextDataList) {
        super(message, contextName, contextDataList);
    }

    public DaoException(String message, String contextName, Map contextDataMap) {
        super(message, contextName, contextDataMap);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(String message, Throwable cause, String contextName, Object... contextDataList) {
        super(message, cause, contextName, contextDataList);
    }

    public DaoException(String message, Throwable cause, String contextName, Map contextDataMap) {
        super(message, cause, contextName, contextDataMap);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(Throwable cause, String contextName, Object... contextDataList) {
        super(cause, contextName, contextDataList);
    }

    public DaoException(Throwable cause, String contextName, Map contextDataMap) {
        super(cause, contextName, contextDataMap);
    }
}
