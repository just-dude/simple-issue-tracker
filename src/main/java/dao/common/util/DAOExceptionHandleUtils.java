/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common.dao.util;

import com.google.gson.Gson;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import service.common.dao.exception.DAOException;

/**
 *
 * @author SuslovAI
 */
public class DAOExceptionHandleUtils {
    
    
    public static DAOException  handleExceptionAndReturnDAOException(Exception e,Logger log, Object entity,String operationName){
        log.error("DAOException is occured on "+operationName+" entity: "+entity.toString(),e);
        if(e instanceof DAOException){
            return (DAOException)e;
        } else{
            return new DAOException("DAO exception has occured",e);
        }
    }
    
    public static DAOException handleExceptionAndReturnDAOException(Exception e,Logger log, List<Object> entities,String operationName){
        log.error("DAOException is occured on "+operationName+" entities: "+StringUtils.join(entities,", "),e);
        if(e instanceof DAOException){
            return (DAOException)e;
        } else{
            return new DAOException("DAO exception has occured",e);
        }
    }
    
    public static DAOException handleExceptionAndReturnDAOException(Exception e,Logger log, Map<String,String> parameters,String operationName){
        log.error("DAOException is occured on "+operationName+", parameters: "+new Gson().toJson(parameters),e);
        if(e instanceof DAOException){
            return (DAOException)e;
        } else{
            return new DAOException("DAO exception has occured",e);
        }
    }
    
    public static DAOException handleExceptionAndReturnDAOException(Exception e,Logger log, Map.Entry<String,String> parameter,String operationName){
        log.error("DAOException is occured on "+operationName+", parameters: {"+parameter.getKey()+" : "+parameter.getValue()+"}",e);
        if(e instanceof DAOException){
            return (DAOException)e;
        } else{
            return new DAOException("DAO exception has occured",e);
        }
    }
    
    public static DAOException handleExceptionAndReturnDAOException(Exception e,Logger log,String operationName){
        log.error("DAOException is occured on "+operationName,e);
        if(e instanceof DAOException){
            return (DAOException)e;
        } else{
            return new DAOException("DAO exception has occured",e);
        }
    }
}
