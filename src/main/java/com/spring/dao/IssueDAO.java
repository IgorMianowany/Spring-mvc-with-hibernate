package com.spring.dao;

import com.spring.entity.Issue;

import java.util.List;

public interface IssueDAO {

    List<Issue> getIssues();

    void saveIssue(Issue customer);

    Issue getIssue(int id);

    void deleteIssue(int id);

    List<Issue> searchIssue(String searchName);

    void getJson();
}

