package domain.issues;

import com.google.common.collect.Sets;
import common.argumentAssert.Assert;
import common.beanFactory.BeanFactoryProvider;
import domain.common.HavingNameAndSoftDeletedAndOneIdDomainObject;
import domain.common.HavingOneIdAndSoftDeletedDomainObject;
import domain.common.exception.*;
import org.apache.commons.collections.ListUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "IssueTypes")
public class IssueType extends HavingNameAndSoftDeletedAndOneIdDomainObject<IssueType> {

    public static String ISSUE_STATES_INIT_PATH="issueStates";

    @OneToMany(mappedBy = "issueType",targetEntity = IssueState.class)
    private List<IssueState> issueStates;

    public IssueType() {
    }

    public IssueType(Long id) {
        super(id, null);
    }

    public IssueType(String name) {
        super(null, name);
    }

    public IssueType(Long id, String name, List<IssueState> issueStates) {
        super(id, name);
        this.issueStates = issueStates;
    }

    public List<IssueState> getIssueStates() {
        return issueStates;
    }

    public IssueState getInitialIssueState() {
        Assert.notNull(getId(),"IssueType.id");
        IssueType issueType=getFinder().findOneWithInitPaths(getId(), Sets.newHashSet(IssueType.ISSUE_STATES_INIT_PATH));
        for(IssueState issueState: issueType.getIssueStates()){
            if(issueState.isInitialState()){
                return issueState;
            }
        }
        return null;
    }

    public void setIssueStates(List<IssueState> issueStates) {
        this.issueStates = issueStates;
    }

    public void addIssueState(IssueState issueState){
        if(getIssueStates()==null){
            setIssueStates(new ArrayList<IssueState>());
        }
        getIssueStates().add(issueState);
    }

    @Override
    public <S extends IssueType> S save() throws DataAccessFailedBuisnessException {
        throw new UnsupportedOperationException("Method is unsupproted. Pleaase use save(Map<Integer,List<Integer>> issueStateIndexToTransferIssueStateIndexesListMap) method instead");
    }

    @Transactional
    public IssueType save(Map<Integer,List<Integer>> issueStateIndexToTransferIssueStateIndexesListMap) throws DataIntegrityViolationBusinessException,DataAccessFailedBuisnessException {
        Assert.notNull(getIssueStates());
        Assert.notEmpty(getIssueStates());
        Assert.notContainsNullElements(getIssueStates());
        Assert.notNull(issueStateIndexToTransferIssueStateIndexesListMap);
        Assert.notEmpty(issueStateIndexToTransferIssueStateIndexesListMap);
        Assert.notContainsNullElements(issueStateIndexToTransferIssueStateIndexesListMap);

        IssueType current =super.save();
        if(isNew()){
            setId(current.getId());
        }else{
            removeOldIssueStatesNotFoundInNewIssueStatesList();
        }
        saveIssueStatesAndSetupTransitionStates(issueStateIndexToTransferIssueStateIndexesListMap);
        return current;
    }




    protected List<IssueState> saveIssueStatesAndSetupTransitionStates(
            Map<Integer,List<Integer>> issueStateIndexToTransitionIssueStateIndexesListMap){
        List<IssueState> savedIssueStatesList = new ArrayList<>(getIssueStates().size());
        for(int i=0;i<getIssueStates().size();i++){
            getIssueStates().get(i).setIssueType(this);
            IssueState savedIssueState=getIssueStates().get(i).save();
            savedIssueStatesList.add(savedIssueState);
        }

        for(int i=0;i<savedIssueStatesList.size();i++){
            List<IssueState> issueStatesToTransition = new ArrayList<>();
            if(!issueStateIndexToTransitionIssueStateIndexesListMap.containsKey(i)){
                continue;
            }
            for (Integer index:issueStateIndexToTransitionIssueStateIndexesListMap.get(i)){
                issueStatesToTransition.add(savedIssueStatesList.get(index));
            }
            savedIssueStatesList.get(i).setIssueStatesToTransition(issueStatesToTransition);
            savedIssueStatesList.get(i).save();
        }
        return savedIssueStatesList;
    }

    protected void removeOldIssueStatesNotFoundInNewIssueStatesList() throws DataIntegrityViolationBusinessException{
        IssueType currentIssueType = getFinder().findOneWithInitPaths(getId(),Sets.newHashSet(IssueType.ISSUE_STATES_INIT_PATH));
        if(currentIssueType==null){
            return;
        }
        List<IssueState> issueStatesForRemove = currentIssueType.getIssueStates();
        issueStatesForRemove=getLeftOutersection(issueStatesForRemove,getIssueStates());
        IssuesFinder issuesFinder = (IssuesFinder)BeanFactoryProvider.getBeanFactory().getBean("issuesFinder");
        if(issuesFinder.findIssuesInOneOfIssueStates(issueStatesForRemove).size()>0){
            throw new DataIntegrityViolationBusinessException("Data integrity violation has occured. Can't edit issue type because issues states reference to one of issue states that not include in edited issue type.");
        }
        for(IssueState issueState:issueStatesForRemove){
            issueState.remove();
        }
    }

    protected List<IssueState> getLeftOutersection(List<IssueState> firstList,List<IssueState> secondList){
        List<IssueState> result=new ArrayList();
        for(IssueState first: firstList){
            boolean isInSecondList=false;
            for(IssueState second: secondList){
                if(first.equalsById(second)){
                    isInSecondList=true;
                    break;
                }
            }
            if(!isInSecondList){
                result.add(first);
            }
        }
        return result;
    }

    @Transactional
    @Override
    public void remove() throws DataAccessFailedBuisnessException, EntityWithSuchIdDoesNotExistsBusinessException, DataIntegrityViolationBusinessException {
        Assert.notNull(getId(),"issueType.id");
        IssueType issueType = getFinder().findOne(getId());
        for(IssueState issueState: issueType.issueStates){
            issueState.remove();
        }
        super.remove();
    }


    @Override
    public void doInitializePaths(HashSet<String> pathsForInitialization) {
        if(pathsForInitialization.contains(ISSUE_STATES_INIT_PATH)) {
            getIssueStates().size();
        }
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
