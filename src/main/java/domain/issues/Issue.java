package domain.issues;

import common.beanFactory.BeanFactoryProvider;
import domain.common.HavingNameAndOneIdDomainObject;
import domain.common.exception.DataAccessFailedBuisnessException;
import domain.common.exception.EntityWithSuchIdDoesNotExistsBusinessException;
import domain.users.UserAccount;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "Issues")
public class Issue extends HavingNameAndOneIdDomainObject<Issue> {

    public static enum Priority {
        Low, Medium, Hight
    }

    private String description;
    private String additionalAttributes;
    //@OneToMany
    //private List<File> attachments;
    @OneToOne
    @JoinColumn(name = "stateId", foreignKey = @ForeignKey(name = "id"))
    private IssueState state;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @OneToOne
    @JoinColumn(name = "authorId", foreignKey = @ForeignKey(name = "id"))
    private UserAccount author;

    @OneToOne
    @JoinColumn(name = "assigneeId", foreignKey = @ForeignKey(name = "id"))
    private UserAccount assignee;

    @OneToMany
    private List<Issue> subIssues;

    @OneToOne
    @JoinColumn(name = "typeId", foreignKey = @ForeignKey(name = "id"))
    private IssueType type;
    private ZonedDateTime createdDateTime;
    private ZonedDateTime requiredResolvedDateTime;
    private ZonedDateTime resolvedDateTime;
    private Boolean isSoftDeleted = false;

    public Issue() {
    }

    public Issue(Long id, String name, String description, String additionalAttributes, IssueState state, Priority priority, UserAccount author, UserAccount assignee, List<Issue> subIssues, IssueType type, ZonedDateTime createdDateTime, ZonedDateTime requiredResolvedDateTime, ZonedDateTime resolvedDateTime) {
        super(id, name);
        this.description = description;
        this.additionalAttributes = additionalAttributes;
        this.state = state;
        this.priority = priority;
        this.author = author;
        this.assignee = assignee;
        this.subIssues = subIssues;
        this.type = type;
        this.createdDateTime = createdDateTime;
        this.requiredResolvedDateTime = requiredResolvedDateTime;
        this.resolvedDateTime = resolvedDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdditionalAttributes() {
        return additionalAttributes;
    }

    public void setAdditionalAttributes(String additionalAttributes) {
        this.additionalAttributes = additionalAttributes;
    }

    public IssueState getState() {
        return state;
    }

    public void setState(IssueState state) {
        this.state = state;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public UserAccount getAuthor() {
        return author;
    }

    public void setAuthor(UserAccount author) {
        this.author = author;
    }

    public UserAccount getAssignee() {
        return assignee;
    }

    public void setAssignee(UserAccount assignee) {
        this.assignee = assignee;
    }

    public List<Issue> getSubIssues() {
        return subIssues;
    }

    public void setSubIssues(List<Issue> subIssues) {
        this.subIssues = subIssues;
    }

    public IssueType getType() {
        return type;
    }

    public void setType(IssueType type) {
        this.type = type;
    }

    public ZonedDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(ZonedDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public ZonedDateTime getRequiredResolvedDateTime() {
        return requiredResolvedDateTime;
    }

    public void setRequiredResolvedDateTime(ZonedDateTime requiredResolvedDateTime) {
        this.requiredResolvedDateTime = requiredResolvedDateTime;
    }

    public ZonedDateTime getResolvedDateTime() {
        return resolvedDateTime;
    }

    public void setResolvedDateTime(ZonedDateTime resolvedDateTime) {
        this.resolvedDateTime = resolvedDateTime;
    }

    protected void setSoftDeleted() {
        this.isSoftDeleted = true;
    }

    @Transactional
    @Override
    public <S extends Issue> S save() throws DataAccessFailedBuisnessException {
        setCreatedDateTime(ZonedDateTime.now(getClock()));
        return super.save();
    }

    private Clock getClock() {
        return (Clock) BeanFactoryProvider.getBeanFactory().getBean("clock");
    }

    @Transactional
    @Override
    public void remove() throws DataAccessFailedBuisnessException, EntityWithSuchIdDoesNotExistsBusinessException {
        this.isSoftDeleted = true;
        Issue entityInStorage = getFinder().getOne(getId());
        entityInStorage.setSoftDeleted();
        entityInStorage.save();
    }
}
