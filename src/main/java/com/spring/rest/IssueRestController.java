package com.spring.rest;

import com.spring.entity.Issue;
import com.spring.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class IssueRestController {

    @Autowired
    private IssueService issueService;

    @GetMapping("/issues")
    public List<Issue> getIssues(){

        return issueService.getIssues();


    }

}
