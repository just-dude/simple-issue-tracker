package domain.issues;

import domain.common.HavingNameAndOneIdDomainObject;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "IssueTypes")
public class IssueType extends HavingNameAndOneIdDomainObject<IssueType> {

    @OneToMany(mappedBy = "issueType",targetEntity = IssueState.class)
    private List<IssueState> issueStates;

    public IssueType() {
    }

    public IssueType(Long id, String name, List<IssueState> issueStates) {
        super(id, name);
        this.issueStates = issueStates;
    }

    public List<IssueState> getIssueStates() {
        return issueStates;
    }

    public void setIssueStates(List<IssueState> issueStates) {
        this.issueStates = issueStates;
    }
}
