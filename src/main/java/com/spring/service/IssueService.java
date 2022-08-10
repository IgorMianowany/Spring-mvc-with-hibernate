package com.spring.service;

import com.spring.entity.Issue;

import java.util.List;

public interface IssueService {

    List<Issue> getIssues();

    void saveIssue(Issue customer);

    Issue getIssue(int id);

    void deleteIssue(int id);

    List<Issue> searchIssue(String searchName);
}

