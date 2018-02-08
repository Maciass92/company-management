package com.example.companymanagementsystem.bootstrap;

import com.example.companymanagementsystem.model.AdvancePayments;
import com.example.companymanagementsystem.model.Employee;
import com.example.companymanagementsystem.model.WorkingHours;
import com.example.companymanagementsystem.repositories.AdvancePaymentsRepository;
import com.example.companymanagementsystem.repositories.EmployeeRepository;
import com.example.companymanagementsystem.repositories.WorkingHoursRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final AdvancePaymentsRepository advancePaymentsRepository;
    private final WorkingHoursRepository workingHoursRepository;
    private final EmployeeRepository employeeRepository;

    public EmployeeBootstrap(AdvancePaymentsRepository advancePaymentsRepository, WorkingHoursRepository workingHoursRepository, EmployeeRepository employeeRepository) {
        this.advancePaymentsRepository = advancePaymentsRepository;
        this.workingHoursRepository = workingHoursRepository;
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

        chris.addWorkingHours(new WorkingHours(8, LocalDate.now()));
        chris.addWorkingHours(new WorkingHours(7, LocalDate.parse("2018-02-05")));
        chris.addWorkingHours(new WorkingHours(6, LocalDate.parse("2018-02-04")));

        chris.addAdvancePayment(new AdvancePayments(300, LocalDate.now()));
        chris.addAdvancePayment(new AdvancePayments(100, LocalDate.parse("2018-02-05")));
        chris.addAdvancePayment(new AdvancePayments(200, LocalDate.parse("2018-02-04")));
        chris.addAdvancePayment(new AdvancePayments(150, LocalDate.parse("2018-02-03")));

        employees.add(chris);

        //Sample Employee no.2

        Employee john = new Employee();
        john.setName("John Kowalski");
        john.setAddress("Lombard Street, Gdańsk, 14-123");
        john.setContact("111-222-333");
        john.setWage(15);

        john.addWorkingHours(new WorkingHours(3, LocalDate.now()));
        john.addWorkingHours(new WorkingHours(4, LocalDate.parse("2018-01-05")));
        john.addWorkingHours(new WorkingHours(2, LocalDate.parse("2018-02-04")));

        john.addAdvancePayment(new AdvancePayments(15, LocalDate.now()));
        john.addAdvancePayment(new AdvancePayments(22, LocalDate.parse("2018-01-05")));
        john.addAdvancePayment(new AdvancePayments(7, LocalDate.parse("2018-02-01")));
        john.addAdvancePayment(new AdvancePayments(14, LocalDate.parse("2018-03-03")));

        employees.add(john);

        return employees;
    }

}
