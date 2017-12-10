package controller.struts.action.main.issues.issueTypes;

import com.google.common.collect.Sets;
import common.beanFactory.BeanFactoryProvider;
import controller.struts.action.common.FormProviderHandlingExceptionsBaseAction;
import domain.common.Finder;
import domain.issues.IssueState;
import domain.issues.IssueType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SuslovAI on 06.10.2017.
 */
public class AddIssueTypeAction extends WriteIssueTypeAction {

    protected static final Logger LOG = LogManager.getLogger(AddIssueTypeAction.class);

    @Override
    protected Logger getLogger() {
        return LOG;
    }
}
