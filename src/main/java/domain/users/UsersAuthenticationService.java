/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.users;

import ru.zabgu.service.common.business.exception.BusinessException;
import ru.zabgu.service.concreteService.users.userAccounts.model.UserLoginAndPassword;

/**
 * @author Suslovai
 */
public interface UsersAuthenticationService {

    void login(UserLoginAndPassword userLoginAndPassword) throws BusinessException;

    void logout();

}
