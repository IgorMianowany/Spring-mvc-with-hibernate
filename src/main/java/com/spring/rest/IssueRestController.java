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
        if(issueId>getNumberOfIssues()+1 || issueId<1){
            throw new IssueNotFoundException("Issue with id of " + issueId + " not found");
        }
        return issueService.getIssue(issueId);
    }

    private int getNumberOfIssues(){
        return issueService.getIssues().size();
    }

    @PostMapping("/issues")
    public Issue addIssue(@RequestBody Issue issue){

        // in case the id is passed in JSON we set the id to 0
        // this forces hibernate to insert new issue into database instead of updating existing one

        issue.setId(0);
        issueService.saveIssue(issue);

        return issue;
    }

    @PutMapping("/issues")
    public Issue updateIssue(@RequestBody Issue issue){
        issueService.saveIssue(issue);

        return issue;
    }

    @DeleteMapping("/issues/{issueId}")
    public String deleteIssue(@PathVariable int issueId){
        Issue tempIssue = issueService.getIssue(issueId);

        if(tempIssue != null){
            issueService.deleteIssue(issueId);
            return "Deleted issue with id of " + issueId;
        }
        return "No issue with that id found";
    }

}
