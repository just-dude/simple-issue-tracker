package domain.issues;

import common.argumentAssert.Assert;
import common.beanFactory.BeanFactoryProvider;
import domain.common.HavingNameAndOneIdDomainObject;
import domain.common.exception.BusinessException;
import domain.common.exception.DataAccessFailedBuisnessException;
import domain.common.exception.DataIntegrityViolationBusinessException;
import domain.common.exception.EntityWithSuchIdDoesNotExistsBusinessException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "IssueTypes")
public class IssueType extends HavingNameAndOneIdDomainObject<IssueType> {

    @OneToMany(mappedBy = "issueType",targetEntity = IssueState.class)
    private List<IssueState> issueStates;

    public IssueType() {
    }

    public IssueType(Long id) {
        super(id, null);
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

    @Transactional
    public List<IssueState> saveIssueStatesAndTransitions(List<IssueState> issueStatesList,
                     Map<Integer,List<Integer>> issueStateIndexToTransferIssueStateIndexesListMap) throws BusinessException{
        Assert.notNull(issueStatesList);
        Assert.notEmpty(issueStatesList);
        Assert.notContainsNullElements(issueStatesList);
        Assert.notNull(issueStateIndexToTransferIssueStateIndexesListMap);
        Assert.notEmpty(issueStateIndexToTransferIssueStateIndexesListMap);
        Assert.notContainsNullElements(issueStateIndexToTransferIssueStateIndexesListMap);

        removeAllStatesByTypeIfExist(getId());
        List<IssueState> savedIssueStatesList = new ArrayList<>(issueStatesList.size());
        for(int i=0;i<issueStatesList.size();i++){
            issueStatesList.get(i).setIssueType(this);
            IssueState savedIssueState=issueStatesList.get(i).save();
            savedIssueStatesList.add(savedIssueState);
        }
        for(int i=0;i<savedIssueStatesList.size();i++){
            List<IssueState> issueStatesToTransition = new ArrayList<>();
            if(!issueStateIndexToTransferIssueStateIndexesListMap.containsKey(i)){
                continue;
            }
            for (Integer index:issueStateIndexToTransferIssueStateIndexesListMap.get(i)){
                issueStatesToTransition.add(savedIssueStatesList.get(index));
            }
            savedIssueStatesList.get(i).setIssueStatesToTransition(issueStatesToTransition);
            savedIssueStatesList.get(i).save();
        }
        return savedIssueStatesList;
    }

    protected void removeAllStatesByTypeIfExist(Long typeId) throws BusinessException{
        IssueStatesFinder issueStatesFinder = (IssueStatesFinder) BeanFactoryProvider.getBeanFactory().getBean("issueStatesFinder");
        List<IssueState> issueStateList = issueStatesFinder.findAllByIssueType(typeId);
        for(IssueState issueState: issueStateList){
            issueState.remove();
        }
    }

    @Override
    public void remove() throws DataAccessFailedBuisnessException, EntityWithSuchIdDoesNotExistsBusinessException, DataIntegrityViolationBusinessException {
        Assert.notNull(getId(),"issueType.id");
        removeAllStatesByTypeIfExist(getId());
        super.remove();
    }

    @Override
    public String toString() {
        return "IssueType{" +
                "id="+id+
                ", name="+name+
                ", issueStates=" + issueStates +
                '}';
    }
}
