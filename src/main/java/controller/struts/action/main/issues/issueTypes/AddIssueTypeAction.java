package controller.struts.action.main.issues.issueTypes;

import controller.struts.action.common.FormProviderHandlingExceptionsBaseAction;
import domain.issues.IssueState;
import domain.issues.IssueType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * Created by SuslovAI on 06.10.2017.
 */
public class AddIssueTypeAction extends FormProviderHandlingExceptionsBaseAction {

    protected static final Logger LOG = LogManager.getLogger(AddIssueTypeAction.class);

    protected IssueType issueType = new IssueType();
    protected Map<Integer,List<Integer>> issueStateIndexToTransferIssueStateIndexesListMap;

    protected String initialStateName;
    protected String transitionsStatesNames;
    protected String standartFinishStatesNames;
    protected String nonStandartFinishStatesNames;


    private List<IssueState> issueStates;


    @Override
    protected void doExecute() throws Exception {
        issueType.setIssueStates(issueStates);
        issueType.save(getIssueStateIndexToTransferIssueStateIndexesListMap(issueStates.size()));
        issueType = new IssueType();
    }

    protected void buildIssueStatesListAndIndexesToTransferMapFromInputStrings(){

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
        issueType = new IssueType("New issueType type");
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
