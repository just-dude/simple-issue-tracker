package controller.struts.action.admin.users;

import common.beanFactory.BeanFactoryProvider;
import controller.struts.action.common.FormProviderHandlingExceptionsBaseAction;
import domain.common.Finder;
import domain.users.UserGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Created by SuslovAI on 06.10.2017.
 */
public class UserGroupsManagementAction extends FormProviderHandlingExceptionsBaseAction {

    protected static final Logger LOG = LogManager.getLogger(UserGroupsManagementAction.class);

    private static final Integer entitiesPerPage = 10;

    private Integer pageNumber = 1;

    private Long forRemoveUserGroupId = null;
    private Page<UserGroup> userGroupsPage;

    @Override
    protected void doExecute() throws Exception {
        try {
            if (forRemoveUserGroupId != null) {
                UserGroup userGroup = new UserGroup();
                userGroup.setId(forRemoveUserGroupId);
                userGroup.remove();
            }
        } catch (Exception e) {
            throw e;
        } finally {
            loadUserGroupsPage();
        }
    }

    @Override
    protected void doInput() throws Exception {
        loadUserGroupsPage();
    }

    private void loadUserGroupsPage() {
        Finder<UserGroup, Long> userGroupsFinder = (Finder<UserGroup, Long>) BeanFactoryProvider.getBeanFactory().getBean("userGroupsFinder");
        userGroupsPage = userGroupsFinder.findAll(new PageRequest(pageNumber - 1, entitiesPerPage));
    }

    public Long getForRemoveUserGroupId() {
        return forRemoveUserGroupId;
    }

    public void setForRemoveUserGroupId(Long forRemoveUserGroupId) {
        this.forRemoveUserGroupId = forRemoveUserGroupId;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getTotalPagesCount() {
        return this.userGroupsPage.getTotalPages();
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Page<UserGroup> getUserGroupsPage() {
        return userGroupsPage;
    }


    @Override
    protected Logger getLogger() {
        return LOG;
    }
}
