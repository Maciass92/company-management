package com.example.companymanagementsystem.services;

import com.example.companymanagementsystem.commands.EmployeeCommand;
import com.example.companymanagementsystem.converters.EmployeeCommandToEmployee;
import com.example.companymanagementsystem.converters.EmployeeToEmployeeCommand;
import com.example.companymanagementsystem.model.Employee;
import com.example.companymanagementsystem.repositories.EmployeeRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeCommandToEmployee employeeCommandToEmployee;
    private final EmployeeToEmployeeCommand employeeToEmployeeCommand;

    public EmployeeServiceImplementation(EmployeeRepository employeeRepository, EmployeeCommandToEmployee employeeCommandToEmployee, EmployeeToEmployeeCommand employeeToEmployeeCommand) {
        this.employeeRepository = employeeRepository;
        this.employeeCommandToEmployee = employeeCommandToEmployee;
        this.employeeToEmployeeCommand = employeeToEmployeeCommand;
    }

    @Override
    public Set<Employee> getEmployees() {

        Set<Employee> employeeList = new HashSet<>();

        employeeRepository.findAll().iterator().forEachRemaining(employeeList::add);

        return employeeList;
    }

    @Override
    public Employee findEmployeeById(Long id) {

        Optional<Employee> employee;

        employee = employeeRepository.findById(id);

        if (!employee.isPresent()) {
            System.out.println("Employee with an id: " + id.toString() + " not found.");
            return null;
        }

        return employee.get();
    }

    @Override
    @Transactional
    public EmployeeCommand saveEmployeeCommand(EmployeeCommand employeeCommand) {

        Employee detachedEmployee = employeeCommandToEmployee.convert(employeeCommand);

        Employee savedEmployee = employeeRepository.save(detachedEmployee);

        return employeeToEmployeeCommand.convert(savedEmployee);
    }

    @Override
    @Transactional
    public EmployeeCommand findCommandById(Long id) {

        EmployeeCommand employeeCommand = employeeToEmployeeCommand.convert(findEmployeeById(id));

        return employeeCommand;
    }

    @Override
    public void deleteEmployee(Long id) {

        employeeRepository.deleteById(id);

    }
}
