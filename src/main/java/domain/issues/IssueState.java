package domain.issues;

import domain.common.HavingNameAndSoftDeletedAndOneIdDomainObject;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name = "IssueStates")
public class IssueState extends HavingNameAndSoftDeletedAndOneIdDomainObject<IssueState> {

    public static String ISSUE_STATES_TO_TRANSITION_INIT_PATH="issueStatesToTransition";

    private Boolean isInitialState = false;
    private Boolean isFinishState = false;

    @OneToMany
    private List<IssueState> issueStatesToTransition;

    @ManyToOne
    @JoinColumn(name = "issueTypeId", foreignKey = @ForeignKey(name = "id"))
    private IssueType issueType;

    public IssueState() {
    }

    public IssueState(Long id) {
        super(id,null);
    }

    public IssueState(Long id, String name, Boolean isInitialState, Boolean isFinishState, List<IssueState> issueStatesToTransition, IssueType issueType) {
        super(id, name);
        this.isInitialState = isInitialState;
        this.isFinishState = isFinishState;
        this.issueStatesToTransition = issueStatesToTransition;
        this.issueType = issueType;
    }

    public IssueType getIssueType() {
        return issueType;
    }

    public void setIssueType(IssueType issueType) {
        this.issueType = issueType;
    }

    public Boolean isInitialState() {
        return isInitialState;
    }

    public void setInitialState(Boolean initialState) {
        isInitialState = initialState;
    }

    public Boolean isFinishState() {
        return isFinishState;
    }

    public void setFinishState(Boolean finishState) {
        isFinishState = finishState;
    }

    public List<IssueState> getIssueStatesToTransition() {
        return issueStatesToTransition;
    }

    public void setIssueStatesToTransition(List<IssueState> issueStatesToTransition) {
        this.issueStatesToTransition = issueStatesToTransition;
    }

    public boolean equalsById(IssueState anotherIssueState){
        return (getId()==anotherIssueState.getId());
    }

    @Override
    protected void doInitializePaths(HashSet<String> pathsForInitialization) {
        if(pathsForInitialization.contains(ISSUE_STATES_TO_TRANSITION_INIT_PATH)){
            getIssueStatesToTransition().size();
        }
    }

    @Override
    public String toString() {
        return "IssueState{" +
                "id="+id+
                ", name="+name+
                ", isInitialState=" + isInitialState +
                ", isFinishState=" + isFinishState +
                //", issueStatesToTransition=" + issueStatesToTransition +
                ", issueType.id=" + issueType.getId() +
                '}';
    }
}
