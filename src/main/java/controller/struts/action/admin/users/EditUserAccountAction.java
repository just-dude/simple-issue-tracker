package controller.struts.action.admin.users;

import common.beanFactory.BeanFactoryProvider;
import controller.struts.action.common.FormProviderHandlingExceptionsBaseAction;
import domain.common.Finder;
import domain.users.UserAccount;
import domain.users.UserGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SuslovAI on 06.10.2017.
 */
public class EditUserAccountAction extends FormProviderHandlingExceptionsBaseAction {

    protected static final Logger LOG = LogManager.getLogger(EditUserAccountAction.class);


    private Long userAccountId;
    private UserAccount userAccount = new UserAccount();


    @Override
    protected void doInput() throws Exception {
        Finder<UserAccount, Long> userAccountsFinder = (Finder<UserAccount, Long>) BeanFactoryProvider.getBeanFactory().getBean("userAccountsFinder");
        userAccount = userAccountsFinder.findOne(userAccountId);
    }

    @Override
    protected void doExecute() throws Exception {
        userAccount = userAccount.save();
    }

    public Map<String, String> findAllUserGroups() {
        HashMap<String, String> result = new HashMap<>();
        for (UserGroup priority : UserGroup.values()) {
            result.put(priority.name(), priority.name());
        }
        return result;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }

    @Override
    protected Logger getLogger() {
        return LOG;
    }
}
