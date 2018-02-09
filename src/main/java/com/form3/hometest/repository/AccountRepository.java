package com.form3.hometest.repository;

import com.form3.hometest.entity.Account;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AccountRepository {

    private final SessionFactory sessionFactory;
    
    @Autowired
    public AccountRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Account save(Account account) {
        sessionFactory.getCurrentSession().saveOrUpdate(account);
        return account;
    }

    @SuppressWarnings("unchecked")
    public List<Account> getAllAccounts() {
        return sessionFactory.getCurrentSession().createCriteria(Account.class).list();
    }

    public Account findById(String accountNumber) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Account.class);
        criteria.add(Restrictions.eq("accountNumber", accountNumber));
        return (Account) criteria.list().get(0);
    }

    @SuppressWarnings("unchecked")
    public List<Account> getAccountsByName(String name) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Account.class);

        if (!StringUtils.isEmpty(name)) {
            criteria.add(Restrictions.eq("name", name));
        }
        return criteria.list();
    }

    public void delete(Account account) {
        sessionFactory.getCurrentSession().delete(account);
    }

    public boolean exists(String accountNumber) {
        return findById(accountNumber) != null;
    }
}
