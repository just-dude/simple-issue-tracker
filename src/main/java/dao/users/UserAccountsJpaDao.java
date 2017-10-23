package dao.users;

import dao.common.JpaDAO;
import dao.common.exception.DaoException;
import domain.users.AccountInfo_;
import domain.users.UserAccount;
import domain.users.UserAccount_;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by SuslovAI on 23.10.2017.
 */
public class UserAccountsJpaDao extends JpaDAO<UserAccount, Long> {

    public UserAccountsJpaDao(EntityManager em) {
        super(em, UserAccount.class);
    }

    public UserAccount findByLogin(String login) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserAccount> cq = cb.createQuery(getEntityTypeClass());
        Root<UserAccount> root = cq.from(getEntityTypeClass());
        cq.select(root);
        cq.where(cb.equal(root.get(UserAccount_.accountInfo).get(AccountInfo_.login), login));
        TypedQuery<UserAccount> typedQuery = em.createQuery(cq);
        List<UserAccount> userAccountList = typedQuery.getResultList();
        if (userAccountList.size() > 1) {
            throw new DaoException("More than one UserAccount entity was found by login " + login);
        }
        return (userAccountList.size() > 0) ? userAccountList.get(0) : null;
    }
}