package com.example.companymanagementsystem.services;

import com.example.companymanagementsystem.model.Employee;
import com.example.companymanagementsystem.repositories.EmployeeRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImplementation(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
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
}
