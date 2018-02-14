package com.example.companymanagementsystem.services;

import com.example.companymanagementsystem.commands.WorkdayCommand;
import com.example.companymanagementsystem.model.Workday;

import java.time.LocalDate;

public interface WorkdayService {

    Workday findByDate(LocalDate date);

    WorkdayCommand saveWorkdayCommand(WorkdayCommand workdayCommand);
}
