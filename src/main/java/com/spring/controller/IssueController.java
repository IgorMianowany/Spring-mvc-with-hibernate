package com.spring.controller;

import com.spring.entity.Customer;
import com.spring.entity.Issue;
import com.spring.service.CustomerService;
import com.spring.service.IssueService;
import com.spring.utils.SortUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/issue")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @RequestMapping("/list")
    public String listIssues(Model model){
        // get customers from service
        List<Issue> issues;

        issues = issueService.getIssues();


        // add customers to model
        model.addAttribute("issues", issues);

        return "list-issues";
    }


}
