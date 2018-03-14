package com.example.companymanagementsystem.commands;

import com.example.companymanagementsystem.model.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class WorkdayCommand {

    private Long id;
    private Integer hoursWorked;
    private Integer advancePayment;
    private Long employeeId;
    private Employee employee;
}
