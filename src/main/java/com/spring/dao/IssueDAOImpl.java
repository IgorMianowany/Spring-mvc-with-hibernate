package com.spring.dao;

import com.spring.entity.Customer;
import com.spring.entity.Issue;
import com.spring.utils.SortUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IssueDAOImpl implements IssueDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Issue> getIssues() {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "from Issue order by id" ;
        Query<Issue> theQuery = session.createQuery(queryString, Issue.class);

        return theQuery.getResultList();
    }

    @Override
    public void saveIssue(Issue issue) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(issue);
    }

    @Override
    public Issue getIssue(int id) {

        return sessionFactory.getCurrentSession().get(Issue.class, id);
    }

    @Override
    public void deleteIssue(int id) {
        sessionFactory.getCurrentSession().createQuery("delete from Issue where id="+id).executeUpdate();
    }

    @Override
    public List<Issue> searchIssue(String searchName) {
        Session session = sessionFactory.getCurrentSession();

        Query query = null;

        if(!searchName.isBlank()){
            query = session.createQuery("from Issue where lower(description) like :searchName", Issue.class);
            query.setParameter("searchName", "%" + searchName.toLowerCase() + "%");
        }
        else {
            return getIssues();
        }

        return (List<Issue>) query.getResultList();
    }
}
