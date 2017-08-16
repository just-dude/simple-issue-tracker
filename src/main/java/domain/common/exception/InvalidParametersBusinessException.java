/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.common.exception;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Map;


public class InvalidParametersBusinessException extends BusinessException {
    
    private Map<String,Object> invalidParameters;

    public InvalidParametersBusinessException(Map<String, Object> invalidParameters) {
        super("InvalidParameters is occured. Invalid parameters list: "+new GsonBuilder().serializeNulls().create().toJson(invalidParameters));
        this.invalidParameters = invalidParameters;
    }

    public Map<String, Object> getInvalidParameters() {
        return invalidParameters;
    }
}
