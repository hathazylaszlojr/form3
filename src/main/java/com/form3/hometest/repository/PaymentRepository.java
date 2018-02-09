package com.form3.hometest.repository;

import com.form3.hometest.entity.Payment;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class PaymentRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public PaymentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Payment savePayment(Payment payment) {
        sessionFactory.getCurrentSession().saveOrUpdate(payment);
        return payment;
    }

    @SuppressWarnings("unchecked")
    public List<Payment> getAllPayments() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Payment.class);
        return criteria.list();
    }

    public Payment getById(UUID id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Payment.class);
        criteria.add(Restrictions.eq("id", id.toString()));
        List results = criteria.list();
        if (results.isEmpty()) {
            return null;
        }
        if (results.size() > 1) {
            throw new IllegalStateException("There are multiple payment entries with the same id.");
        }

        return (Payment) results.get(0);
    }

    public boolean exists(UUID id) {
        return getById(id) != null;
    }

    public void delete(Payment payment) {
        sessionFactory.getCurrentSession().delete(payment);
    }
}
