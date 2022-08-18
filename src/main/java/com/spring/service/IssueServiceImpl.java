package com.spring.service;

import com.spring.dao.IssueDAO;
import com.spring.entity.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueDAO issueDAO;

    @Override
    public List<Issue> getIssues() {
        return issueDAO.getIssues();
    }

    @Override
    public void saveIssue(Issue issue) {
        issueDAO.saveIssue(issue);
    }

    @Override
    public Issue getIssue(int id) {
        return issueDAO.getIssue(id);
    }

    @Override
    public void deleteIssue(int id) {
        issueDAO.deleteIssue(id);
    }

    @Override
    public List<Issue> searchIssue(String searchName) {
       return issueDAO.searchIssue(searchName);
    }

    @Override
    public void getJson() {
        issueDAO.getJson();
    }
}
