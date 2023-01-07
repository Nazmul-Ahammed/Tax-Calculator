package com.repository;

import com.domain.Adminauthority;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorityRepositoryImpl implements AuthorityRepository {

    private SessionFactory sessionFactory;

    public AuthorityRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Adminauthority> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Adminauthority> authorityQuery = session.createQuery("from Adminauthority", Adminauthority.class);
        return authorityQuery.getResultList();
    }

    public Adminauthority create(Adminauthority adminAuthority) {
        Session session = sessionFactory.getCurrentSession();
        session.save(adminAuthority);
        return adminAuthority;
    }

    public Adminauthority get(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Adminauthority.class, id);
    }

    public Adminauthority update(Adminauthority adminAuthority) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(adminAuthority);
        return adminAuthority;
    }

    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Adminauthority adminAuthority = get(id);
        if (adminAuthority != null) {
            session.delete(adminAuthority);
        }
    }
}
