package com.example.companymanagementsystem.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
public class WorkdayCommand {

    private Long id;
    private Integer hoursWorked;
    private Integer advancePayment;
    private LocalDate date;
}
