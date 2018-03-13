package com.example.companymanagementsystem.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class WorkdayCommand {

    private Long employeeId;
    private Integer hoursWorked;
    private Integer advancePayment;
}
