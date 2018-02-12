package com.example.companymanagementsystem.controllers;

import com.example.companymanagementsystem.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping("/employees")
    public String showEmployees(Model model) {

        model.addAttribute("employees", employeeService.getEmployees());
        model.addAttribute("dateTime", LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT)));

        return "employees";
    }

    @RequestMapping("/employee/{id}")
    public String showSpecificEmployee(@PathVariable String id, Model model){

        model.addAttribute("employee", employeeService.findEmployeeById(new Long(id)));

        return "specificEmployee";
    }
}
