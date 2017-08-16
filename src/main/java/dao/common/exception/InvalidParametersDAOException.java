
package service.common.dao.exception;

import com.google.gson.Gson;
import java.util.Map;


public class InvalidParametersDAOException extends DAOException {

    private Map<String,Object> invalidParameters;

    public InvalidParametersDAOException(Map<String, Object> invalidParameters) {
        super("InvalidParameters is occured. Invalid parameters list: "+new Gson().toJson(invalidParameters));
        this.invalidParameters = invalidParameters;
    }

    public Map<String, Object> getInvalidParameters() {
        return invalidParameters;
    }
}
