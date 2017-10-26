package dao.users;

import dao.common.JpaDao;
import dao.common.exception.DaoException;
import domain.users.UserAccount;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by SuslovAI on 23.10.2017.
 */
public class UserAccountsJpaDao extends JpaDao<UserAccount, Long> {

    public UserAccountsJpaDao(EntityManager em) {
        super(em, UserAccount.class);
    }

    public UserAccount findByLogin(String login) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserAccount> cq = cb.createQuery(getEntityTypeClass());
        Root<UserAccount> root = cq.from(getEntityTypeClass());
        cq.select(root);
        //cq.where(cb.equal(root.get(UserAccount_.accountInfo).get(AccountInfo_.login), login));
        cq.where(cb.equal(root.get("accountInfo").get("login"), login));
        TypedQuery<UserAccount> typedQuery = em.createQuery(cq);
        List<UserAccount> userAccountList = typedQuery.getResultList();
        if (userAccountList.size() > 1) {
            throw new DaoException("More than one UserAccount entity was found by login " + login);
        }
        return (userAccountList.size() > 0) ? userAccountList.get(0) : null;
    }
}
