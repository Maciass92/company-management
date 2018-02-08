package com.example.companymanagementsystem.controllers;

import com.example.companymanagementsystem.repositories.EmployeeRepository;
import com.example.companymanagementsystem.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String showEmployees(Model model) {

        model.addAttribute("employees", employeeService.getEmployees());

        return "employees";
    }
}
