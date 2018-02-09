package com.example.companymanagementsystem.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Workday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer hoursWorked;
    private LocalDate date;

    @ManyToOne
    private Employee employee;

    public Workday(Integer hoursWorked, LocalDate date) {
        this.hoursWorked = hoursWorked;
        this.date = date;
    }
}