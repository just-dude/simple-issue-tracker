
package dao.common.exception;

import com.google.gson.Gson;
import java.util.Map;


public class IllegalArgumentDAOException extends DAOException {

    private static String MESSAGE = "Illegal argument(s) present in contexts data";

    public IllegalArgumentDAOException() {
    }

    public IllegalArgumentDAOException(String contextName, Object... contextDataList) {
        super(MESSAGE, contextName, contextDataList);
    }

    public IllegalArgumentDAOException(String contextName, Map contextDataMap) {
        super(MESSAGE, contextName, contextDataMap);
    }

    public IllegalArgumentDAOException(String message) {
        super(message);
    }

    public IllegalArgumentDAOException(String message, String contextName) {
        super(message, contextName);
    }

    public IllegalArgumentDAOException(String message, String contextName, Object... contextDataList) {
        super(message, contextName, contextDataList);
    }

    public IllegalArgumentDAOException(String message, String contextName, Map contextDataMap) {
        super(message, contextName, contextDataMap);
    }


}
