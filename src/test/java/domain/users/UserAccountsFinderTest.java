package domain.users;

import common.DBTestCase;
import common.beanFactory.BeanFactoryProvider;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;

/**
 * Created by Администратор on 30.10.2017.
 */
public class UserAccountsFinderTest extends DBTestCase {

    public UserAccountsFinderTest() {
        super("UserAccountsFinderTest");
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(getDataSetAsInputStream("testDataSet/users/UserAccountsFinderSetupDataset.xml"));
    }


    private UserAccountsFinder getFinder(){
        return (UserAccountsFinder) BeanFactoryProvider.getBeanFactory().getBean("userAccountsFinder");
    }

    @Test
    public void findByLogin() throws Exception {
        UserAccount userAccount = getFinder().findByLogin("login1");
        assertNotNull(userAccount);
        assertEquals(1L,(long)userAccount.getId());
    }

}