package com.example.companymanagementsystem.bootstrap;

import com.example.companymanagementsystem.model.Employee;
import com.example.companymanagementsystem.model.Workday;
import com.example.companymanagementsystem.repositories.EmployeeRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final EmployeeRepository employeeRepository;

    public EmployeeBootstrap( EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void onApplicationEvent(ContextRefreshedEvent event){

        employeeRepository.saveAll(getEmployees());
    }

    public List<Employee> getEmployees(){

        List<Employee> employees = new ArrayList<>();

        //Sample Employee no.1

        Employee chris = new Employee();
        chris.setName("Chris Nowak");
        chris.setAddress("Oak Avenue 32, Warszawa, 12-123");
        chris.setContact("500-600-700");
        chris.setWage(20);

        chris.addWorkday(new Workday(8, LocalDate.now(), 200));
        chris.addWorkday(new Workday(7, LocalDate.parse("2018-02-05"), 300));
        chris.addWorkday(new Workday(6, LocalDate.parse("2018-02-04"), 50));

        employees.add(chris);

        //Sample Employee no.2

        Employee john = new Employee();
        john.setName("John Kowalski");
        john.setAddress("Lombard Street, Gdańsk, 14-123");
        john.setContact("111-222-333");
        john.setWage(15);

        john.addWorkday(new Workday(3, LocalDate.now(), 150));
        john.addWorkday(new Workday(4, LocalDate.parse("2018-01-05"), 1500));
        john.addWorkday(new Workday(2, LocalDate.parse("2018-02-04"), 40));

        employees.add(john);

        return employees;
    }

}
