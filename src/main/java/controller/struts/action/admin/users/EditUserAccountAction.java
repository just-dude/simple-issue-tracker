package controller.struts.action.admin.users;

import common.beanFactory.BeanFactoryProvider;
import controller.struts.action.common.FormProviderHandlingExceptionsBaseAction;
import domain.common.Finder;
import domain.users.UserAccount;
import domain.users.UserGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static common.beanFactory.BeanFactoryProvider.getBeanFactory;

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
        userAccount = userAccountsFinder.getOne(userAccountId);
    }

    @Override
    protected void doExecute() throws Exception {
        userAccount = userAccount.save();
    }

    public Map<String, String> findAllUserGroups() {
        Map<String, String> userGroupIdWithGroupNamesMap = null;
        try {
            Finder<UserGroup, Long> userGroupsFinder = (Finder<UserGroup, Long>) getBeanFactory().getBean("userGroupsFinder");
            List<UserGroup> allUserGroups = userGroupsFinder.findAll();
            userGroupIdWithGroupNamesMap = new LinkedHashMap(allUserGroups.size());
            Collections.sort(allUserGroups, (UserGroup ug1, UserGroup ug2) -> {
                return ug1.getName().compareTo(ug2.getName());
            });
            for (UserGroup userGroup : allUserGroups) {
                userGroupIdWithGroupNamesMap.put(userGroup.getId().toString(), userGroup.getName());
            }
        } catch (Exception e) {
            userGroupIdWithGroupNamesMap = new LinkedHashMap(0);
            LOG.debug("Exception has occured on EditUserAccountAction.getAllUserGroups", e);
        }
        return userGroupIdWithGroupNamesMap;
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
