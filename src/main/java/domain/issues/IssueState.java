package domain.issues;

import domain.common.HavingNameAndOneIdDomainObject;

import javax.persistence.*;

@Entity
@Table(name = "IssueStates")
public class IssueState extends HavingNameAndOneIdDomainObject<IssueState> {

    @ManyToOne
    @JoinColumn(name = "issueTypeId", foreignKey = @ForeignKey(name = "id"))
    private IssueType issueType;

    public IssueState() {
    }

    public IssueState(Long id, String name, IssueType issueType) {
        super(id, name);
        this.issueType = issueType;
    }

    public IssueType getIssueType() {
        return issueType;
    }

    public void setIssueType(IssueType issueType) {
        this.issueType = issueType;
    }
}
