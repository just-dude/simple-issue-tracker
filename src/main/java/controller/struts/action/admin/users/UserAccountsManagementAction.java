package controller.struts.action.admin.users;

import common.beanFactory.BeanFactoryProvider;
import controller.struts.action.common.FormProviderHandlingExceptionsBaseAction;
import domain.common.Finder;
import domain.users.UserAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Created by SuslovAI on 06.10.2017.
 */
public class UserAccountsManagementAction extends FormProviderHandlingExceptionsBaseAction {

    protected static final Logger LOG = LogManager.getLogger(UserAccountsManagementAction.class);

    private static final Integer entitiesPerPage = 10;

    private Long forRemoveUserId = null;
    private Integer pageNumber = 1;

    private Page<UserAccount> userAccountsPage;

    @Override
    protected void doExecute() throws Exception {
        try {
            if (forRemoveUserId != null) {
                UserAccount userAccount = new UserAccount();
                userAccount.setId(forRemoveUserId);
                userAccount.remove();
            }
        } catch (Exception e) {
            throw e;
        } finally {
            loadUserAccountsPage();
        }
    }

    @Override
    protected void doInput() throws Exception {
        loadUserAccountsPage();
    }

    private void loadUserAccountsPage() {
        Finder<UserAccount, Long> userAccountsFinder = (Finder<UserAccount, Long>) BeanFactoryProvider.getBeanFactory().getBean("userAccountsFinder");
        userAccountsPage = userAccountsFinder.findAll(new PageRequest(pageNumber - 1, entitiesPerPage));
    }

    public Long getForRemoveUserId() {
        return forRemoveUserId;
    }

    public void setForRemoveUserId(Long forRemoveUserId) {
        this.forRemoveUserId = forRemoveUserId;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getTotalPagesCount() {
        return this.userAccountsPage.getTotalPages();
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Page<UserAccount> getUserAccountsPage() {
        return userAccountsPage;
    }


    @Override
    protected Logger getLogger() {
        return LOG;
    }
}
