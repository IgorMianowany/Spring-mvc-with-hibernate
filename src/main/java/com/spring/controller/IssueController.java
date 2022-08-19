package com.spring.controller;

import com.spring.entity.Issue;
import com.spring.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/issue")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @RequestMapping(value="{name}", method = RequestMethod.GET)
    public @ResponseBody Issue getIssue(@PathVariable String name) {

        Issue issue = new Issue();

        issue.setCustomer(name);
        issue.setId(20);
        return issue;
    }

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
        return "issue-update-form";
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

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserNameSimple(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return principal.getName();
    }

    @RequestMapping("/to_json")
    public String getJson(){

        issueService.getJson();

        return "redirect:/issue/list";
    }

    @RequestMapping(name = "/getJson",method = RequestMethod.GET)
    public void doDownload(HttpServletRequest request,
                           HttpServletResponse response) throws IOException {

        issueService.getJson();


        ServletContext context = request.getServletContext();
        String appPath = context.getRealPath("");
        System.out.println("appPath = " + appPath);

        // construct the complete absolute path of the file
        String fullPath = "C:/Users/fimia/IdeaProjects/Spring-mvc-with-hibernate/src/main/resources/json_output.json";
        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);

        // get MIME type of the file
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);

        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // get output stream of the response
        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outStream.close();

    }

}
