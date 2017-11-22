package controller.struts.action.main.issues;

import com.google.common.collect.Sets;
import common.beanFactory.BeanFactoryProvider;
import controller.struts.action.common.FormProviderHandlingExceptionsBaseAction;
import domain.common.Finder;
import domain.issues.IssueState;
import domain.issues.IssueType;
import domain.issues.IssuesFinder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SuslovAI on 06.10.2017.
 */
public class EditIssueTypeAction extends FormProviderHandlingExceptionsBaseAction {

    protected static final Logger LOG = LogManager.getLogger(EditIssueTypeAction.class);

    private Long issueTypeId;
    private IssueType issueType = new IssueType();
    private List<IssueState> issueStates;


    @Override
    protected void doExecute() throws Exception {
        issueType.setIssueStates(issueStates);
        issueType.save(getIssueStateIndexToTransferIssueStateIndexesListMap(issueStates.size()));
        issueType = new IssueType();
    }



    protected Map<Integer,List<Integer>> getIssueStateIndexToTransferIssueStateIndexesListMap(int issueStatesSize){
        Map<Integer,List<Integer>> map = new HashMap<>();
        for(int i=0;i<issueStatesSize-2;i++){
            map.put(i, Arrays.asList(i+1));
            map.put(i, Arrays.asList(issueStatesSize-1));
        }
        return map;
    }

    @Override
    protected void doInput() throws Exception {
        issueType = getIssueTypesFinder().findOneWithInitPaths(issueTypeId, Sets.newHashSet(IssueType.ISSUE_STATES_INIT_PATH));
        setIssueStates(issueType.getIssueStates());
    }

    protected Finder<IssueType,Long> getIssueTypesFinder(){
        return (Finder<IssueType,Long>) BeanFactoryProvider.getBeanFactory().getBean("issueTypesFinder");
    }



    public List<IssueState> getIssueStates() {
        return issueStates;
    }

    public void setIssueStates(List<IssueState> issueStates) {
        this.issueStates = issueStates;
    }

    @Override
    protected Logger getLogger() {
        return LOG;
    }
}
