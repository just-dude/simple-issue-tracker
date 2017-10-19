/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.users;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import ru.zabgu.service.common.business.exception.BusinessException;
import ru.zabgu.service.common.business.exception.ValidationFailedException;
import ru.zabgu.service.common.validation.constraintDescription.ConstraintDescription;
import ru.zabgu.service.common.validation.constraintViolation.EntityConstraintViolation;
import ru.zabgu.service.concreteService.users.userAccounts.model.UserLoginAndPassword;

import java.util.Collections;

/**
 * @author Suslovai
 */
public class DefaultUsersAuthenticationService implements UsersAuthenticationService {

    private static final Logger LOG = LogManager.getLogger(DefaultUsersAuthenticationService.class);

    public DefaultUsersAuthenticationService() {
    }

    @Override
    public void login(UserLoginAndPassword userLoginAndPassword) throws BusinessException {
        UsernamePasswordToken token = new UsernamePasswordToken(userLoginAndPassword.getLogin(), userLoginAndPassword.getPassword());

        try {
            SecurityUtils.getSubject().login(token);
        } catch (AuthenticationException e) {
            LOG.error("Login failed", e);
            throw new ValidationFailedException(Collections.singletonList(
                    new EntityConstraintViolation(new ConstraintDescription("invalid.login.or.password"), "userLoginAndPassword")
            ));
        } catch (Exception e) {
            LOG.error("Login unexpected failed", e);
            throw new BusinessException("Login unexpected failed", e);
        }
    }


    @Override
    public void logout() {
        try {
            SecurityUtils.getSubject().logout();
        } catch (Exception e) {
            LOG.error("logout unexpected failed", e);
            throw new BusinessException("logout unexpected failed", e);
        }
    }


}
