package controller.struts.action.admin.users;

import common.beanFactory.BeanFactoryProvider;
import controller.struts.action.common.FormProviderHandlingExceptionsBaseAction;
import domain.common.Finder;
import domain.users.UserGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by SuslovAI on 06.10.2017.
 */
public class EditUserGroupAction extends FormProviderHandlingExceptionsBaseAction {

    protected static final Logger LOG = LogManager.getLogger(EditUserGroupAction.class);


    private Long userGroupId;
    private UserGroup userGroup = new UserGroup();


    @Override
    protected void doInput() throws Exception {
        Finder<UserGroup, Long> userGroupsFinder = (Finder<UserGroup, Long>) BeanFactoryProvider.getBeanFactory().getBean("userGroupsFinder");
        userGroup = userGroupsFinder.getOne(userGroupId);
    }

    @Override
    protected void doExecute() throws Exception {
        userGroup = userGroup.save();
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public Long getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Long userGroupId) {
        this.userGroupId = userGroupId;
    }

    @Override
    protected Logger getLogger() {
        return LOG;
    }
}
