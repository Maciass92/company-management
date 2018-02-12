package com.example.companymanagementsystem.services;

import com.example.companymanagementsystem.commands.EmployeeCommand;
import com.example.companymanagementsystem.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface EmployeeService{

    Set<Employee> getEmployees();

    Employee findEmployeeById(Long id);

    EmployeeCommand saveEmployeeCommand(EmployeeCommand employeeCommand);
}
