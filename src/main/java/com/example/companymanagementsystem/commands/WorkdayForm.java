package com.example.companymanagementsystem.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class WorkdayForm {

    private List<WorkdayCommand> workdayCommands;
}
