package controller.struts.action.main;

import common.beanFactory.BeanFactoryProvider;
import controller.struts.action.common.CustomResults;
import controller.struts.action.common.HandlingExceptionsBaseAction;
import controller.struts.action.common.util.ActionUtils;
import domain.users.Profile;
import domain.users.UserAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;


public class IndexAction extends HandlingExceptionsBaseAction {

    private static final Logger LOG = LogManager.getLogger(IndexAction.class);

    public IndexAction() {
        super();
    }

    @Override
    public String doExecute() throws Exception {
        LOG.info("IndexAction is executing");
        JpaRepository<UserAccount, Long> jpaDAO = (JpaRepository<UserAccount, Long>) BeanFactoryProvider.getBeanFactory().getBean("userAccountsDAO");
        UserAccount ua = (UserAccount) BeanFactoryProvider.getBeanFactory().getBean("userAccount");
        ua.setProfile(new Profile("namein spring", "sur", "email"));
        ua.save();
        LOG.info("UserAccount " + ua);
        LOG.info("IndexAction has executed");
        return ActionUtils.getDecoratedByViewSettingsResult(CustomResults.SUCCESS);
    }

    @Override
    protected void logException(Exception e, String message) {
        ActionUtils.logException(LOG, e, message);
    }

}
