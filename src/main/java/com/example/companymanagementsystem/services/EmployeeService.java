package com.example.companymanagementsystem.services;

import com.example.companymanagementsystem.commands.EmployeeCommand;
import com.example.companymanagementsystem.model.Employee;

import java.util.List;

public interface EmployeeService{

    List<Employee> getEmployees();

    Employee findEmployeeById(Long id);

    EmployeeCommand saveEmployeeCommand(EmployeeCommand employeeCommand);

    EmployeeCommand findCommandById(Long id);

    void deleteEmployee(Long id);

    Employee findEmployeeWithFilteredWorkdaysAndPayments(Long id, String date);
}
