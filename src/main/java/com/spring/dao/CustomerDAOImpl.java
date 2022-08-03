package com.spring.dao;

import com.spring.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO{

    // session factory injection
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {

        // get current session
        Session session = sessionFactory.getCurrentSession();

        // return query with list of customers from db
        return session.createQuery("from Customer c order by c.lastName ", Customer.class).getResultList();
    }

    @Override
    public void saveCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(customer);


    }

    @Override
    public Customer getCustomer(int id) {
        return sessionFactory.getCurrentSession().get(Customer.class, id);
    }

    @Override
    public void deleteCustomer(int id) {
//        sessionFactory.getCurrentSession().createQuery("delete from Customer where id="+id).executeUpdate();
        Session session = sessionFactory.getCurrentSession();
        Customer customer = session.get(Customer.class, id);
        session.delete(customer);
    }
}
