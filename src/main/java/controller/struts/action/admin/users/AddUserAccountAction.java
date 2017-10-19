package controller.struts.action.admin.users;

import controller.struts.action.common.FormProviderHandlingExceptionsBaseAction;
import domain.users.UserAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

/**
 * Created by SuslovAI on 06.10.2017.
 */
public class AddUserAccountAction extends FormProviderHandlingExceptionsBaseAction {

    protected static final Logger LOG = LogManager.getLogger(AddUserAccountAction.class);

    private UserAccount userAccount = new UserAccount();

    @Override
    protected void doExecute() throws Exception {
        userAccount.save();
        userAccount = new UserAccount();
    }

    public Map<String, String> findAllUserGroups() {
        return getEntitiesIdWithNamesMapByFinderName("userGroupsFinder");
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    protected Logger getLogger() {
        return LOG;
    }
}
