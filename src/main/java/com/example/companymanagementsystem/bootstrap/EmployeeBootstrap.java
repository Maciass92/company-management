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

        //Sample Employee no.3

        Employee ann = new Employee();
        ann.setName("Ann Meyer");
        ann.setAddress("Catalonya Street, Valencia, 14-123");
        ann.setContact("54 123 45 23");
        ann.setWage(44);

        ann.addWorkday(new Workday(8, LocalDate.now()));
        ann.addWorkday(new Workday(8, LocalDate.parse("2018-01-05")));
        ann.addWorkday(new Workday(12, LocalDate.parse("2018-02-04"), 2500));

        employees.add(ann);

        return employees;
    }

}
