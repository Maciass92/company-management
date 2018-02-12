package com.example.companymanagementsystem.model;

import lombok.Data;

import javax.persistence.*;
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
    private List<Workday> workdays = new ArrayList<>();

    public void addWorkday(Workday workday){

        this.workdays.add(workday);
        workday.setEmployee(this);
    }
}
