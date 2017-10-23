package domain.users;

import dao.users.UserAccountsJpaDao;
import domain.common.SimpleFinder;
import domain.common.exception.BusinessException;

/**
 * Created by SuslovAI on 23.10.2017.
 */
public class UserAccountsFinder extends SimpleFinder<UserAccount, Long> {

    public UserAccountsFinder(UserAccountsJpaDao dao) {
        super(dao);
    }

    public UserAccount findByLogin(String login) {
        try {
            return ((UserAccountsJpaDao) dao).findByLogin(login);
        } catch (Exception e) {
            throw new BusinessException("Exception has occured", e);
        }
    }

}
