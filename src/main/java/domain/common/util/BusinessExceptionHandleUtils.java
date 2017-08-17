/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.common.util;

import com.google.gson.Gson;
import java.util.List;
import java.util.Map;

import dao.common.exception.DAOException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import domain.common.exception.BusinessException;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

/**
 *
 * @author SuslovAI
 */
public class BusinessExceptionHandleUtils {

    private static final Marker EXCPECTED_EXCEPTION_MARKER = MarkerManager.getMarker("EXCPECTED EXCEPTION");
    private static final Marker UNEXPECTED_EXCEPTION_MARKER = MarkerManager.getMarker("UNEXPECTED EXCEPTION");

    
    /*public static BusinessException  handleExceptionAndReturnBusinessException(Exception e,Logger log){
        log.error(UNEXPECTED_EXCEPTION_MARKER,"BusinessException is occured",e);
        if(e instanceof BusinessException){
            return (BusinessException)e;
        } else{
            return new BusinessException("Business exception has occured",e);
        }
    }
    
    public static BusinessException handleExceptionAndReturnBusinessException(DAOException e, Logger log){
        log.error(EXCPECTED_EXCEPTION_MARKER,"BusinessException is occured on "+operationName+" models: "+StringUtils.join(models,", "),e);
         if(e instanceof BusinessException){
            return (BusinessException)e;
        } else{
            return new BusinessException("Business exception has occured",e);
        }
    }*/
}
