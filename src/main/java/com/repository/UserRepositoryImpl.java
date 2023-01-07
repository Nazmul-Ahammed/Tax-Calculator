package com.repository;


import com.domain.Adminuser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private SessionFactory sessionFactory;

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Adminuser> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Adminuser> userQuery = session.createQuery("from Adminuser", Adminuser.class);
        return userQuery.getResultList();
    }

    public Adminuser create(Adminuser adminUser) {
        Session session = sessionFactory.getCurrentSession();
        session.save(adminUser);
        return adminUser;
    }

    public Adminuser get(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Adminuser.class, id);
    }

    public Adminuser update(Adminuser adminUser) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(adminUser);
        return adminUser;
    }
    public Adminuser updatepassword(Adminuser adminUser) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(adminUser);
        return adminUser;
    }

    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Adminuser adminUser = get(id);
        if (adminUser != null) {
            session.delete(adminUser);
        }
    }

    @Override
    public Adminuser getByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query<Adminuser> userQuery = session.createQuery("from Adminuser where username = :username", Adminuser.class);
        userQuery.setParameter("username", username);
        return userQuery.getSingleResult();
    }
}
