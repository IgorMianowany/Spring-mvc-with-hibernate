package com.spring.dao;


import com.spring.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findUserByName(String username) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<User> theQuery = currentSession.createQuery("from User where userName=:uName", User.class);
        theQuery.setParameter("uName", username);
        User theUser = null;
        try {
            theUser = theQuery.getSingleResult();
        } catch (Exception e) {
            theUser = null;
        }
        return theUser;
    }

    @Override
    public void save(User user) {

        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(user);

    }

    @Override
    public List<User> getUsers() {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "from User order by id";
        Query<User> theQuery = session.createQuery(queryString, User.class);

        return theQuery.getResultList();
    }

    @Override
    public User getUser(int id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }
}
