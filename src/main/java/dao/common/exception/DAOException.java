
package dao.common.exception;


import common.beanFactory.exception.ApplicationException;

import java.util.Map;

public class DAOException extends ApplicationException {


    public DAOException() {
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, String contextName) {
        super(message, contextName);
    }

    public DAOException(String message, String contextName, Object... contextDataList) {
        super(message, contextName, contextDataList);
    }

    public DAOException(String message, String contextName, Map contextDataMap) {
        super(message, contextName, contextDataMap);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(String message, Throwable cause, String contextName, Object... contextDataList) {
        super(message, cause, contextName, contextDataList);
    }

    public DAOException(String message, Throwable cause, String contextName, Map contextDataMap) {
        super(message, cause, contextName, contextDataMap);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

    public DAOException(Throwable cause, String contextName, Object... contextDataList) {
        super(cause, contextName, contextDataList);
    }

    public DAOException(Throwable cause, String contextName, Map contextDataMap) {
        super(cause, contextName, contextDataMap);
    }
}
