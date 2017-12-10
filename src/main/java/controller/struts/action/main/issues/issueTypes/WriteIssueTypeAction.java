package controller.struts.action.main.issues.issueTypes;

import controller.struts.action.common.FormProviderHandlingExceptionsBaseAction;
import domain.common.exception.ValidationFailedException;
import domain.issues.IssueState;
import domain.issues.IssueType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import smartvalidation.validator.entityValidator.EntityValidator;
import smartvalidation.validator.entityValidator.ValidationContext;

import java.util.*;

/**
 * Created by SuslovAI on 06.10.2017.
 */
public class WriteIssueTypeAction extends FormProviderHandlingExceptionsBaseAction {



    protected static final Logger LOG = LogManager.getLogger(WriteIssueTypeAction.class);

    protected IssueType issueType = new IssueType();
    protected Map<Integer,List<Integer>> issueStateIndexToTransferIssueStateIndexesListMap;
    protected List<IssueState> issueStates;

    protected IssueStatesFields issueStatesFields;

    @Override
    protected void doExecute() throws Exception {
        IssueStatesFieldsValidatorFactory vf = new IssueStatesFieldsValidatorFactory(
                new ValidationContext("issueStatesFields"));
        EntityValidator validator =vf.getValidator(issueStatesFields);
        if(!validator.isValid()){
            throw new ValidationFailedException(validator.getConstraintViolations());
        }
        issueType.setIssueStates(issueStates);
        issueType.save(getIssueStateIndexToTransferIssueStateIndexesListMap(issueStates.size()));
        issueType = new IssueType();
    }

    protected void buildIssueStatesListAndIndexesTransferMapFromInputStrings(){

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

    public static class IssueStatesFields{
        private String initialStateName;
        private String transitionsStatesNames;
        private String standartFinishStatesNames;
        private String nonStandartFinishStatesNames;

        public IssueStatesFields() {
        }

        public IssueStatesFields(String initialStateName, String transitionsStatesNames, String standartFinishStatesNames, String nonStandartFinishStatesNames) {
            this.initialStateName = initialStateName;
            this.transitionsStatesNames = transitionsStatesNames;
            this.standartFinishStatesNames = standartFinishStatesNames;
            this.nonStandartFinishStatesNames = nonStandartFinishStatesNames;
        }

        public String getInitialStateName() {
            return initialStateName;
        }

        public void setInitialStateName(String initialStateName) {
            this.initialStateName = initialStateName;
        }

        public String getTransitionsStatesNames() {
            return transitionsStatesNames;
        }

        public void setTransitionsStatesNames(String transitionsStatesNames) {
            this.transitionsStatesNames = transitionsStatesNames;
        }

        public String getStandartFinishStatesNames() {
            return standartFinishStatesNames;
        }

        public void setStandartFinishStatesNames(String standartFinishStatesNames) {
            this.standartFinishStatesNames = standartFinishStatesNames;
        }

        public String getNonStandartFinishStatesNames() {
            return nonStandartFinishStatesNames;
        }

        public void setNonStandartFinishStatesNames(String nonStandartFinishStatesNames) {
            this.nonStandartFinishStatesNames = nonStandartFinishStatesNames;
        }
    }
}
