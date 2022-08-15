package com.spring.controller;

import com.spring.entity.CRMUser;
import com.spring.entity.User;
import com.spring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage(Model model){

        model.addAttribute("CRMUser", new CRMUser());

        return "registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(@Valid @ModelAttribute("CRMUser") CRMUser user, BindingResult bindingResult, Model model){

        String username = user.getUsername();

        if (bindingResult.hasErrors()) {
            return "registration-form";
        }

        User existing = userService.findByUserName(username);
        if (existing!=null) {
            model.addAttribute("CRMUser", new CRMUser());
            model.addAttribute("registrationError", "User name already exists.");
            return "registration-form";
        }

        userService.save(user);

        return "registration-confirmation";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

}


