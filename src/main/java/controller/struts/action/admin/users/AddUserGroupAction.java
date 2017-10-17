package controller.struts.action.admin.users;

import controller.struts.action.common.FormProviderHandlingExceptionsBaseAction;
import domain.users.UserGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by SuslovAI on 06.10.2017.
 */
public class AddUserGroupAction extends FormProviderHandlingExceptionsBaseAction {

    protected static final Logger LOG = LogManager.getLogger(AddUserGroupAction.class);

    private UserGroup userGroup = new UserGroup();

    @Override
    protected void doExecute() throws Exception {
        userGroup.save();
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    @Override
    protected Logger getLogger() {
        return LOG;
    }
}
