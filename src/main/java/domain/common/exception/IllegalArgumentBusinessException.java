/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.common.exception;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Map;


public class IllegalArgumentBusinessException extends BusinessException {

    private static String MESSAGE = "Illegal argument(s) present in contexts data";

    public IllegalArgumentBusinessException() {
    }

    public IllegalArgumentBusinessException(String contextName, Object... contextDataList) {
        super(MESSAGE, contextName, contextDataList);
    }

    public IllegalArgumentBusinessException(String contextName, Map contextDataMap) {
        super(MESSAGE, contextName, contextDataMap);
    }

    public IllegalArgumentBusinessException(String message) {
        super(message);
    }

    public IllegalArgumentBusinessException(String message, String contextName) {
        super(message, contextName);
    }

    public IllegalArgumentBusinessException(String message, String contextName, Object... contextDataList) {
        super(message, contextName, contextDataList);
    }

    public IllegalArgumentBusinessException(String message, String contextName, Map contextDataMap) {
        super(message, contextName, contextDataMap);
    }

    public IllegalArgumentBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalArgumentBusinessException(String message, Throwable cause, String contextName, Object... contextDataList) {
        super(message, cause, contextName, contextDataList);
    }

    public IllegalArgumentBusinessException(String message, Throwable cause, String contextName, Map contextDataMap) {
        super(message, cause, contextName, contextDataMap);
    }

    public IllegalArgumentBusinessException(Throwable cause) {
        super(cause);
    }

    public IllegalArgumentBusinessException(Throwable cause, String contextName, Object... contextDataList) {
        super(cause, contextName, contextDataList);
    }

    public IllegalArgumentBusinessException(Throwable cause, String contextName, Map contextDataMap) {
        super(cause, contextName, contextDataMap);
    }
}
