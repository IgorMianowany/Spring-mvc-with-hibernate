package com.spring.dao;

import com.spring.entity.Issue;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class IssueDAOImpl implements IssueDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Issue> getIssues() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("from Issue", Issue.class).getResultList();

//        String queryString = "from Issue";
//        Query<Issue> theQuery = session.createQuery(queryString, Issue.class);
//
//        return theQuery.getResultList();
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
            query = session.createQuery("from Issue where lower(description) like :searchName OR lower(customer) like :searchName", Issue.class);
            query.setParameter("searchName", "%" + searchName.toLowerCase() + "%");

        }
        else {
            return getIssues();
        }
        return (List<Issue>) query.getResultList();
    }

    @Override
    public void getJson() {
        Session session = sessionFactory.getCurrentSession();

        String query = "from Issue";
        List<Issue> issues = session.createQuery(query,Issue.class).getResultList();

        JSONObject jsonObject = new JSONObject();

        JSONArray array = new JSONArray();

        issues.forEach(issue -> {
            JSONObject record = new JSONObject();
            record.put("id",issue.getId());
            record.put("description",issue.getDescription());
            record.put("customer",issue.getCustomer());
            record.put("status",issue.getStatus());
            array.add(record);
        });

        jsonObject.put("Issue_data", array);

        FileWriter file = null;
        try {
            file = new FileWriter("C:/Users/fimia/IdeaProjects/Spring-mvc-with-hibernate/src/main/resources/json_output.json");
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}