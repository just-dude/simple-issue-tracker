/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common.dao.exception;

/**
 *
 * @author SuslovAI
 */
public class UnsupportedOperationDAOException extends DAOException{

    public UnsupportedOperationDAOException(String message) {
        super(message);
    }

    public UnsupportedOperationDAOException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
}