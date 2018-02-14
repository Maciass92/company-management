package com.example.companymanagementsystem.services;

import com.example.companymanagementsystem.commands.EmployeeCommand;
import com.example.companymanagementsystem.converters.EmployeeCommandToEmployee;
import com.example.companymanagementsystem.converters.EmployeeToEmployeeCommand;
import com.example.companymanagementsystem.model.Employee;
import com.example.companymanagementsystem.model.Workday;
import com.example.companymanagementsystem.repositories.EmployeeRepository;
import com.example.companymanagementsystem.repositories.WorkdayRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final WorkdayRepository workdayRepository;
    private final EmployeeCommandToEmployee employeeCommandToEmployee;
    private final EmployeeToEmployeeCommand employeeToEmployeeCommand;

    public EmployeeServiceImplementation(EmployeeRepository employeeRepository, WorkdayRepository workdayRepository, EmployeeCommandToEmployee employeeCommandToEmployee, EmployeeToEmployeeCommand employeeToEmployeeCommand) {
        this.employeeRepository = employeeRepository;
        this.workdayRepository = workdayRepository;
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

    @Override
    public Employee findEmployeeWithFilteredWorkdaysAndPayments(Long id, String date) {

        Employee employee = findEmployeeById(id);

        List<Workday> filteredWorkdays = new ArrayList<>();

        StringBuilder startDateString = new StringBuilder(date);
        startDateString.append("-01");

        LocalDate startDate = LocalDate.parse(startDateString);
        LocalDate endDate = startDate.plusMonths(1);

        workdayRepository.findAllByDateBetween(startDate, endDate).iterator().forEachRemaining(filteredWorkdays::add);

        Collections.sort(filteredWorkdays);

        employee.setWorkdays(filteredWorkdays);

        return employee;
    }


}
