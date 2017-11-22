package domain.issues;

import com.google.common.collect.Sets;
import common.argumentAssert.Assert;
import common.beanFactory.BeanFactoryProvider;
import domain.common.HavingNameAndSoftDeletedAndOneIdDomainObject;
import domain.common.exception.BusinessRuleViolationException;
import domain.common.exception.DataAccessFailedBuisnessException;
import domain.common.exception.EntityWithSuchIdDoesNotExistsBusinessException;
import domain.common.exception.ValidationFailedException;
import domain.issues.authorization.IssuesPermissionStringConstants;
import domain.security.SecuritySubjectUtils;
import domain.users.UserAccount;
import org.springframework.transaction.annotation.Transactional;
import smartvalidation.constraintDescription.ConstraintDescription;
import smartvalidation.constraintViolation.PropertyConstraintViolation;

import javax.persistence.*;
import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.*;

@Entity
@Table(name = "Issues")
public class Issue extends HavingNameAndSoftDeletedAndOneIdDomainObject<Issue> {

    public static String CURRENT_SUBISSUES_INIT_PATH="subIssues";
    public static String ALL_SUBISSUES_INIT_PATH="subIssues.all";
    public static String STATES_TO_TRANSITION_OF_ISSUE_STATE_INIT_PATH ="state.statesToTransition";


    public static enum Priority {
        Low, Medium, Hight
    }

    protected String description;
    protected String additionalAttributes;
    //@OneToMany
    //protected List<File> attachments;
    @OneToOne
    @JoinColumn(name = "stateId", foreignKey = @ForeignKey(name = "id"))
    protected IssueState state;

    @Enumerated(EnumType.STRING)
    protected Priority priority;

    @OneToOne
    @JoinColumn(name = "authorId", foreignKey = @ForeignKey(name = "id"))
    protected UserAccount author;

    @OneToOne
    @JoinColumn(name = "assigneeId", foreignKey = @ForeignKey(name = "id"))
    protected UserAccount assignee;

    @OneToOne
    @JoinColumn(name = "parentId", foreignKey = @ForeignKey(name = "id"))
    protected Issue parent;

    @OneToMany(mappedBy = "parent")
    protected List<Issue> subIssues;

    @OneToOne
    @JoinColumn(name = "typeId", foreignKey = @ForeignKey(name = "id"))
    protected IssueType type;

    protected ZonedDateTime createdDateTime;
    protected ZonedDateTime requiredResolvedDateTime;
    protected ZonedDateTime resolvedDateTime;

    public Issue() {
    }

    public Issue(Long id) {
        super(id,null);
    }

    // Use this constructor to create new issue
    public Issue(String name, String description, IssueType type, UserAccount assignee, Priority priority,
                 ZonedDateTime requiredResolvedDateTime,Issue parent) {
        super(null, name);
        this.description = description;
        this.type = type;
        this.requiredResolvedDateTime = requiredResolvedDateTime;
        this.assignee = assignee;
        this.priority = priority;
        this.author = null;
        this.subIssues = null;
        this.additionalAttributes = null;
        this.state = null;
        this.createdDateTime = null;
        this.resolvedDateTime = null;
        this.parent=parent;
    }

    public Issue(Long id, String name, String description, String additionalAttributes, IssueState state, Priority priority,
                 UserAccount author, UserAccount assignee, List<Issue> subIssues, IssueType type, ZonedDateTime createdDateTime,
                 ZonedDateTime requiredResolvedDateTime, ZonedDateTime resolvedDateTime,Issue parent) {
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
        this.parent=parent;
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

    public Issue getParent() {
        return parent;
    }

    public void setParent(Issue parent) {
        this.parent = parent;
    }

    @Transactional
    public <S extends Issue> S changeRequiredResolvedDateTime(ZonedDateTime newDateTime){
        Issue currentIssue =  getFinder().findOne(getId());
        checkChangeRequiredResolvedDateTimePermission(currentIssue.getAuthor().getId());
        if(currentIssue.getRequiredResolvedDateTime()==null){
            throw new BusinessRuleViolationException("Issue haven't required resolved datetime. Can't change required resolved datetime");
        }
        if(!newDateTime.isAfter(currentIssue.getRequiredResolvedDateTime())){
            throw new ValidationFailedException(
                Arrays.asList(
                        new PropertyConstraintViolation(
                                new ConstraintDescription("validation.newDateTimeGreaterThanOldDateTime"),
                                "newRequiredResolvedDateTime",
                                newDateTime
                        )
                )
            );
        }
        currentIssue.setRequiredResolvedDateTime(newDateTime);
        try {
            return currentIssue.doSave();
        } catch (Exception e) {
            throw new DataAccessFailedBuisnessException("An error has occured on save entity", e, this.getClass().getName() + ".save", this);
        }
    }

    @Transactional
    public <S extends Issue> S goIntoState(IssueState issueStateToTransition)throws DataAccessFailedBuisnessException,ValidationFailedException {
        Assert.notNull(issueStateToTransition,"issueStateToTransition");
        Issue currentIssue =  getFinder().findOne(getId());
        checkGoIntoStatePermission(currentIssue.getAssignee().getId());
        if(hasIssueStateInIssueStatesToTransitionList(issueStateToTransition,currentIssue.getState().getIssueStatesToTransition())){
            currentIssue.setState(issueStateToTransition);
            try {
                return currentIssue.doSave();
            } catch (Exception e) {
                throw new DataAccessFailedBuisnessException("An error has occured on save entity", e, this.getClass().getName() + ".save", this);
            }
        } else{
            throw new ValidationFailedException(Arrays.asList(
                    new PropertyConstraintViolation(
                            new ConstraintDescription(
                                "validation.issueStateToTransitionNotAllowedForCurrentTransition"
                            ),
                            "issue.state",
                            issueStateToTransition
                    )
            ));
        }
    }

    protected void checkGoIntoStatePermission(Long assigneeeId){
        SecuritySubjectUtils.checkPermission(IssuesPermissionStringConstants.INSTANCE.getGoIntoStatePermission(assigneeeId));
    }

    protected void checkChangeRequiredResolvedDateTimePermission(Long authorId){
        SecuritySubjectUtils.checkPermission(IssuesPermissionStringConstants.INSTANCE.getChangeRequiredResolvedDateTimePermission(authorId));
    }

    protected boolean hasIssueStateInIssueStatesToTransitionList(IssueState issueStateToFinding,List<IssueState> issueStatesList){
        for(IssueState issueState: issueStatesList){
            if(issueStateToFinding.equalsById(issueState)){
                return true;
            }
        }
        return false;
    }

    @Transactional
    @Override
    public <S extends Issue> S save() throws DataAccessFailedBuisnessException {
        Assert.notNull(getType(),"issue.type");
        Assert.notNull(getType().getId(),"issue.type.id");
        if(!isNew()){
            throw new BusinessRuleViolationException("Can't change(resave) exists issue");
        }
        setCreatedDateTime(ZonedDateTime.now(getClock()));
        unsetSoftDeleted();
        setAuthor(SecuritySubjectUtils.getCurrentUserAccount());
        setState(getType().getInitialIssueState());
        return super.save();
    }

    private Clock getClock() {
        return (Clock) BeanFactoryProvider.getBeanFactory().getBean("clock");
    }

    @Override
    public void doRemove() throws DataAccessFailedBuisnessException, EntityWithSuchIdDoesNotExistsBusinessException {
        Assert.notNull(getId(),"issue.id");
        checkRemovePermission();
        Issue entityInStorage = getFinder().findOne(getId());
        entityInStorage.setSoftDeleted();
        for(Issue issue:entityInStorage.getSubIssues()){
            issue.remove();
        }
    }

    @Override
    protected void doInitializePaths(HashSet<String> pathsForInitialization) {
        if(pathsForInitialization.contains(CURRENT_SUBISSUES_INIT_PATH) || pathsForInitialization.contains(ALL_SUBISSUES_INIT_PATH)){
            getSubIssues().size();
        }
        if(pathsForInitialization.contains(ALL_SUBISSUES_INIT_PATH)) {
            if (getSubIssues() == null) {
                return;
            }
            for (Issue issue : getSubIssues()) {
                issue.initializePaths(Sets.newHashSet(ALL_SUBISSUES_INIT_PATH));
            }
        }
        if(pathsForInitialization.contains(STATES_TO_TRANSITION_OF_ISSUE_STATE_INIT_PATH)){
            getState().getIssueStatesToTransition().size();
        }
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id="+id+
                ", name="+name+
                ", description='" + description + '\'' +
                ", additionalAttributes='" + additionalAttributes + '\'' +
                ", state=" + state +
                ", priority=" + priority +
                ", author=" + author +
                ", assignee=" + assignee +
                ", assignee=" + assignee +
                ", parent=" + parent +
                ", subIssues=" + subIssues +
                ", type=" + type +
                ", createdDateTime=" + createdDateTime +
                ", requiredResolvedDateTime=" + requiredResolvedDateTime +
                ", resolvedDateTime=" + resolvedDateTime +
                ", isSoftDeleted=" + isSoftDeleted +
                '}';
    }


}
