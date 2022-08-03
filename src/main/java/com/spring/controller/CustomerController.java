package com.spring.controller;

import com.spring.entity.Customer;
import com.spring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/list")
    public String listCustomers(Model model){

        // get customers from service
        List<Customer> customers = customerService.getCustomers();

        // add customers to model
        model.addAttribute("customers", customers);

        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){

        Customer customer = new Customer();

        model.addAttribute("customer", customer);

        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer){

        customerService.saveCustomer(customer);

        return "redirect:/customer/list";
    }

    @RequestMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int id, Model model){

        // get customer from service
        Customer customer = customerService.getCustomer(id);

        // set customer as a model attribute to prepopulate form
        model.addAttribute("customer", customer);

        // send to form
        return "customer-form";
    }

    @RequestMapping("/delete")
    public String deleteUser(@RequestParam("customerId") int id){
        customerService.deleteCustomer(id);

        return "redirect:/customer/list";
    }

    @GetMapping("/search")
    public String searchUser(@RequestParam("searchName") String searchName, Model model){

        List<Customer> customers = customerService.searchCustomers(searchName);

        model.addAttribute("customers", customers);

        return "list-customers";
    }


}
