package domain.issues;

import domain.common.HavingNameAndOneIdDomainObject;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "IssueTypes")
public class IssueType extends HavingNameAndOneIdDomainObject<IssueType> {

    @OneToMany
    private List<IssueState> issueStatesList;

    public IssueType() {
    }

    public IssueType(Long id, String name, List<IssueState> issueStatesList) {
        super(id, name);
        this.issueStatesList = issueStatesList;
    }

    public List<IssueState> getIssueStatesList() {
        return issueStatesList;
    }

    public void setIssueStatesList(List<IssueState> issueStatesList) {
        this.issueStatesList = issueStatesList;
    }
}
