package domain.issues;

import domain.common.HavingNameAndOneIdDomainObject;
import domain.common.exception.DataAccessFailedBuisnessException;
import domain.common.exception.DataIntegrityViolationBusinessException;
import domain.common.exception.EntityWithSuchIdDoesNotExistsBusinessException;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "IssueStates")
public class IssueState extends HavingNameAndOneIdDomainObject<IssueState> {

    private Boolean isInitialState = false;
    private Boolean isFinishState = false;

    @OneToMany(fetch = FetchType.EAGER)
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
    public String toString() {
        return "IssueState{" +
                "id="+id+
                ", name="+name+
                ", isInitialState=" + isInitialState +
                ", isFinishState=" + isFinishState +
                ", issueStatesToTransition=" + issueStatesToTransition +
                ", issueType=" + issueType +
                '}';
    }
}
