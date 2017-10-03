package domain.common;

import common.SpringContextTestCase;
import common.beanFactory.BeanFactoryProvider;
import domain.users.Profile;
import domain.users.UserAccount;
import org.junit.Test;

/**
 * Created by suslovai on 11.09.2017.
 */
public class DomainObjectSaveTest extends SpringContextTestCase {


    @Test
    public void testSave() throws Exception {

        UserAccount newUserAccount = (UserAccount) BeanFactoryProvider.getBeanFactory().getBean("userAccount");
        newUserAccount.setProfile(new Profile("somenamewritingovertransaction", "s", "e"));
        newUserAccount.save();

        System.out.println(newUserAccount);

    }
}