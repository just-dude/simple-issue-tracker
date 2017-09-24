/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.common.util;

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
    
    public static BusinessException handleExceptionAndReturnBusinessException(DaoException e, Logger log){
        log.error(EXCPECTED_EXCEPTION_MARKER,"BusinessException is occured on "+operationName+" models: "+StringUtils.join(models,", "),e);
         if(e instanceof BusinessException){
            return (BusinessException)e;
        } else{
            return new BusinessException("Business exception has occured",e);
        }
    }*/
}
