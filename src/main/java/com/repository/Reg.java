package com.repository;

import com.domain.Adminauthority;
import com.domain.Registration;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Reg {

    private SessionFactory sessionFactory;

    public Reg(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Registration> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Registration> authorityQuery = session.createQuery("from Registration", Registration.class);
        return authorityQuery.getResultList();
    }

    public Registration create(Registration adminAuthority) {
        Session session = sessionFactory.getCurrentSession();
        session.save(adminAuthority);
        return adminAuthority;
    }

    public Registration get(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Registration.class, id);
    }

    public Registration update(Registration adminAuthority) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(adminAuthority);
        return adminAuthority;
    }

    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Registration adminAuthority = get(id);
        if (adminAuthority != null) {
            session.delete(adminAuthority);
        }
    }
}
