package com.spring.controller;

import com.spring.entity.Customer;
import com.spring.entity.Issue;
import com.spring.service.CustomerService;
import com.spring.service.IssueService;
import com.spring.utils.SortUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/showFormForIssueAdd")
    public String showFormForAdd(Model model){

        Issue issue = new Issue();

        model.addAttribute("issue", issue);

        return "issue-form";
    }

    @PostMapping("/saveIssue")
    public String saveCustomer(@ModelAttribute("issue") Issue issue){

        issueService.saveIssue(issue);

        return "redirect:/issue/list";
    }

    @RequestMapping("/showFormForIssueUpdate")
    public String showFormForUpdate(@RequestParam("issueId") int id, Model model){

        // get customer from service
        Issue issue = issueService.getIssue(id);

        // set customer as a model attribute to prepopulate form
        model.addAttribute("issue", issue);

        // send to form
        return "issue-form";
    }

    @RequestMapping("/delete")
    public String deleteIssue(@RequestParam("issueId") int id){
        issueService.deleteIssue(id);

        return "redirect:/issue/list";
    }

    @GetMapping("/search")
    public String searchIssue(@RequestParam("searchName") String searchName, Model model){

        List<Issue> issues = issueService.searchIssue(searchName);

        model.addAttribute("issues", issues);

        return "list-issues";
    }

    @RequestMapping("/leaders")
    public String leaders(){
        return "leaders";
    }

    @RequestMapping("/systems")
    public String systems(){
        return "systems";
    }

}
