package com.example.companymanagementsystem.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String contact;
    private Integer wage;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<WorkingHours> workingHoursList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<AdvancePayments> advancePaymentsList = new ArrayList<>();

    public void addAdvancePayment(AdvancePayments advancePayments){

        this.advancePaymentsList.add(advancePayments);
        advancePayments.setEmployee(this);
    }

    public void addWorkingHours(WorkingHours workingHours){

        this.workingHoursList.add(workingHours);
        workingHours.setEmployee(this);
    }
}
