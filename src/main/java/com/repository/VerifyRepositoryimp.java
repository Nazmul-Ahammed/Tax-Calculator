package com.repository;
import com.domain.Registration;
import com.domain.Sellervarify;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VerifyRepositoryimp implements VarifyRepository{
    private SessionFactory sessionFactory;
    public VerifyRepositoryimp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public List<Registration> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Registration> registrationQuery = session.createQuery("from Registration", Registration.class);

        return registrationQuery.getResultList();
    }
    public Registration update(Registration registration) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(registration);
        return registration;
    }
    public Registration get(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Registration.class, id);
    }
    public Registration create(Registration registration) {
        Session session = sessionFactory.getCurrentSession();
        session.save(registration);
        return registration;
    }
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Registration registration = get(id);
        if (registration != null) {
            session.delete(registration);
        }
    }


}
