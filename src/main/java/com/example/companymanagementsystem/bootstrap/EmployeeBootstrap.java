package com.example.companymanagementsystem.bootstrap;

import com.example.companymanagementsystem.model.Employee;
import com.example.companymanagementsystem.model.Workday;
import com.example.companymanagementsystem.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class EmployeeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final EmployeeRepository employeeRepository;

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

        chris.addWorkday(new Workday(7, LocalDate.parse("2018-02-05"), 300));
        chris.addWorkday(new Workday(6, LocalDate.parse("2018-04-06"), 50));
        chris.addWorkday(new Workday(8, LocalDate.parse("2018-02-07"), 500));
        chris.addWorkday(new Workday(8, LocalDate.parse("2018-05-08"), 250));
        chris.addWorkday(new Workday(4, LocalDate.parse("2018-02-09")));
        chris.addWorkday(new Workday(8, LocalDate.parse("2018-02-10"), 5220));
        chris.addWorkday(new Workday(12, LocalDate.parse("2018-03-11"), 50));
        chris.addWorkday(new Workday(8, LocalDate.parse("2018-02-24"), 5220));
        chris.addWorkday(new Workday(6, LocalDate.parse("2018-02-12"), 13));
        chris.addWorkday(new Workday(8, LocalDate.parse("2018-02-13"), 177));
        chris.addWorkday(new Workday(7, LocalDate.parse("2018-02-14"), 253));
        chris.addWorkday(new Workday(6, LocalDate.parse("2018-02-15"), 454));
        chris.addWorkday(new Workday(12, LocalDate.parse("2018-02-16"), 1111));
        chris.addWorkday(new Workday(14, LocalDate.parse("2018-02-17"), 999));
        chris.addWorkday(new Workday(16, LocalDate.parse("2018-02-18"), 3));
        chris.addWorkday(new Workday(8, LocalDate.parse("2018-02-19"), 44));
        chris.addWorkday(new Workday(8, LocalDate.parse("2018-02-25"), 54));


        employees.add(chris);

        //Sample Employee no.2

        Employee john = new Employee();
        john.setName("John Kowalski");
        john.setAddress("Lombard Street, Gdańsk, 14-123");
        john.setContact("111-222-333");
        john.setWage(15);

        john.addWorkday(new Workday(4, LocalDate.parse("2018-01-05"), 1500));
        john.addWorkday(new Workday(2, LocalDate.parse("2018-02-04"), 40));

        employees.add(john);

        //Sample Employee no.3

        Employee ann = new Employee();
        ann.setName("Ann Meyer");
        ann.setAddress("Catalonya Street, Valencia, 14-123");
        ann.setContact("54 123 45 23");
        ann.setWage(44);

        ann.addWorkday(new Workday(8, LocalDate.parse("2018-01-05")));
        ann.addWorkday(new Workday(12, LocalDate.parse("2018-02-04"), 2500));

        employees.add(ann);

        return employees;
    }

}
