package domain.issues;

import domain.common.HavingNameAndOneIdDomainObject;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "IssueStates")
public class IssueState extends HavingNameAndOneIdDomainObject<IssueState> {

    private Boolean isInitialState = false;
    private Boolean isFinishState = false;

    @OneToMany
    private List<IssueState> issueStatesToTransfer;

    @ManyToOne
    @JoinColumn(name = "issueTypeId", foreignKey = @ForeignKey(name = "id"))
    private IssueType issueType;

    public IssueState() {
    }

    public IssueState(Long id, String name, Boolean isInitialState, Boolean isFinishState, List<IssueState> issueStatesToTransfer, IssueType issueType) {
        super(id, name);
        this.isInitialState = isInitialState;
        this.isFinishState = isFinishState;
        this.issueStatesToTransfer = issueStatesToTransfer;
        this.issueType = issueType;
    }

    public IssueType getIssueType() {
        return issueType;
    }

    public void setIssueType(IssueType issueType) {
        this.issueType = issueType;
    }

    public Boolean getInitialState() {
        return isInitialState;
    }

    public void setInitialState(Boolean initialState) {
        isInitialState = initialState;
    }

    public Boolean getFinishState() {
        return isFinishState;
    }

    public void setFinishState(Boolean finishState) {
        isFinishState = finishState;
    }

    public List<IssueState> getIssueStatesToTransfer() {
        return issueStatesToTransfer;
    }

    public void setIssueStatesToTransfer(List<IssueState> issueStatesToTransfer) {
        this.issueStatesToTransfer = issueStatesToTransfer;
    }
}
