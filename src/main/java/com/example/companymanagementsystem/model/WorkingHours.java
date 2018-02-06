package com.example.companymanagementsystem.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class WorkingHours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amount;
    private LocalDate date;

    @ManyToOne
    private Employee employee;

    public WorkingHours(Integer amount, LocalDate date) {
        this.amount = amount;
        this.date = date;
    }
}
