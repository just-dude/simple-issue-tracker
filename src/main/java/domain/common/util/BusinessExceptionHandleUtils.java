/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.common.util;

import com.google.gson.Gson;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import domain.common.exception.BusinessException;

/**
 *
 * @author SuslovAI
 */
public class BusinessExceptionHandleUtils {
    
    
    public static BusinessException  handleExceptionAndReturnBusinessException(Exception e,Logger log, Object model,String operationName){
        log.error("BusinessException is occured on "+operationName+" model: "+model.toString(),e);
        if(e instanceof BusinessException){
            return (BusinessException)e;
        } else{
            return new BusinessException("Business exception has occured",e);
        }
    }
    
    public static BusinessException handleExceptionAndReturnBusinessException(Exception e,Logger log, List<Object> models,String operationName){
        log.error("BusinessException is occured on "+operationName+" models: "+StringUtils.join(models,", "),e);
         if(e instanceof BusinessException){
            return (BusinessException)e;
        } else{
            return new BusinessException("Business exception has occured",e);
        }
    }
    
    public static BusinessException handleExceptionAndReturnBusinessException(Exception e,Logger log, Map<String,String> parameters,String operationName){
        log.error("BusinessException is occured on "+operationName+", parameters: "+new Gson().toJson(parameters),e);
        if(e instanceof BusinessException){
            return (BusinessException)e;
        } else{
            return new BusinessException("Business exception has occured",e);
        }
    }
    
    public static BusinessException handleExceptionAndReturnBusinessException(Exception e,Logger log, String operationName){
        log.error("BusinessException is occured on "+operationName,e);
        if(e instanceof BusinessException){
            return (BusinessException)e;
        } else{
            return new BusinessException("Business exception has occured",e);
        }
    }
}
