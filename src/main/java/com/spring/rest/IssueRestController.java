package com.spring.rest;

import com.spring.entity.Issue;
import com.spring.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/issues/{issueId}")
    public Issue getIssue(@PathVariable int issueId){
        if(issueId>getNumberOfIssues() || issueId<1){
            throw new IssueNotFoundException("Issue with id of " + issueId + " not found");
        }
        return issueService.getIssue(issueId);
    }

    private int getNumberOfIssues(){
        return issueService.getIssues().size();
    }
}
